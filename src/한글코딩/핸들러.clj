(ns 한글코딩.핸들러
  (:use [미생.기본]
        [한글코딩.뷰]
        [compojure.core])
  (:require [clojure.string :as s]
            [compojure.route :as route]
            [ring.util.codec :as codec]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [clojure.java.io :as io]))

(함수- 주소
  "한글 경로를 처리하기 위해 url 인코딩"
  [주소] (->> (s/split 주소 #"/")
              (사상 codec/url-encode)
              (s/join "/")))

(defroutes 앱라우트
  (GET "/" [] 첫페이지)
  (GET (주소 "/작성자") [] 작성자소개)
  (GET "/:md파일" [md파일]
    (가정 [파일명 (str "/md/" md파일 ".md")]
      (만약 (io/resource (str "public" 파일명))
        (레이아웃 {:제목 md파일} (마크다운 파일명)))))
  (route/not-found 찾을수없어요))

(정의 앱
  (wrap-defaults 앱라우트
                 (assoc-in site-defaults
                           [:responses :content-types]
                           {:mime-types {"md" "text/markdown"}})))
