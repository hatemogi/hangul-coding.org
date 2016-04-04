(ns 한글코딩.핸들러
  (:use [미생.기본]
        [한글코딩.뷰]
        [compojure.core])
  (:require [clojure.string :as s]
            [clojure.java.io :as io]
            [compojure.route :as route]
            [ring.util.codec :as codec]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [clojure.java.io :as io]))

(함수- 주소
  "한글 경로를 처리하기 위해 url 인코딩"
  [경로] (->> (s/split 경로 #"/")
              (사상 codec/url-encode)
              (s/join "/")))

(defroutes 앱라우트
  (GET "/" [] 첫페이지)
  (GET (주소 "/작성자.html") [] 작성자소개)
  (GET (주소 "/언어.html") [] 프로그래밍-언어)
  (GET (주소 "/소통.html") [] 소통)
  (GET "/:md파일.html" [md파일]
    (가정 [파일명 (str "/md/" md파일 ".md")]
      (만약 (io/resource (str "public" 파일명))
        (레이아웃 {:제목 md파일} (리모트마크다운 파일명)))))
  (route/not-found 찾을수없어요))

(함수- 리소스-합치는-미들웨어 [핸들러 합쳐질파일 & 합칠파일목록]
  (가정 [디렉터리 "resources/public/"
         바뀜? (fn [] 참) ;; 현재 둘다 10ms이내에 끝남. 비교할 필요 없음.
         합침! (fn []
                 (println 합쳐질파일 "<-" 합칠파일목록)
                 (time
                  (with-open [쓸거 (io/writer (str 디렉터리 합쳐질파일))]
                    (run! (fn [파일]
                            (with-open [읽을거 (io/reader (str 디렉터리 파일))]
                              (io/copy 읽을거 쓸거)
                              (.write 쓸거 "\n")))
                          합칠파일목록))))]
    (fn [요청]
      (참이면 (바뀜?) (합침!))
      (핸들러 요청))))

(정의 앱
  (-> 앱라우트

      (리소스-합치는-미들웨어 "묶음.css"
                              "css/normalize.css"
                              "css/milligram.min.css"
                              "css/codemirror.css"
                              #_"css/색/github.css"
                              "css/theme/neat.css"
                              "css/theme/neo.css"
                              "css/theme/elegant.css"
                              "css/theme/ambiance.css"
                              "css/theme/mbo.css"
                              "css/theme/ttcn.css"
                              "css/한글코딩.css")

      (리소스-합치는-미들웨어 "묶음.js"
                              "js/jquery-2.2.2.min.js"
                              #_"js/highlight.pack.js"
                              "js/marked.min.js"
                              "js/codemirror.js"
                              "js/mode/clojure/clojure.js"
                              "js/mode/javascript/javascript.js"
                              "js/mode/css/css.js"
                              "js/mode/clike/clike.js"
                              "js/mode/sql/sql.js"
                              "js/한글코딩.js")

      (wrap-defaults (assoc-in site-defaults
                               [:responses :content-types]
                               {:mime-types {"md" "text/markdown"}}))))
