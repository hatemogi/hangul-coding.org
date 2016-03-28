(ns 한글코딩.뷰
  (:use [미생.기본]
        [hiccup.core]
        [hiccup.page])
  (:require [ring.util.response]))

(함수- 머리말 []
  [:nav])

(함수- 꼬리말 []
  [:footer.꼬리말
   [:div "2016 김대현 올림"]])

(함수- GA [코드]
  [:script
   "(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', '" 코드 "', 'auto');
  ga('send', 'pageview');"])

(함수 레이아웃 [속성 & 본문]
  (html5
   [:html {:lang "ko"}
    [:head
     [:title "한글코딩"]
     (map include-css ["/css/milligram.min.css"
                       "/css/codemirror.css"
                       "/css/색/github.css"
                       "/css/theme/neo.css"
                       "/css/한글코딩.css"])]
    [:body.container
     (머리말)
     (into [:main] 본문)
     (꼬리말)
     (map include-js ["/js/jquery-2.2.2.min.js"
                      "/js/highlight.pack.js"
                      "/js/marked.min.js"
                      "/js/codemirror.js"
                      "/js/mode/clojure/clojure.js"
                      "/js/mode/javascript/javascript.js"
                      "/js/mode/sql/sql.js"
                      "/js/한글코딩.js"])
     #_(GA "UA-75606874-1")]
    ]
   ))

(함수 마크다운 [파일명]
  [:div.markdown {:data-markdown 파일명}])

(함수 첫페이지 [요청]
  (레이아웃 {:제목 "페이지 제목"}
            [:h1 "한글로 코딩하자"]
            [:blockquote "한글로 쓰자 체증이 풀렸다"]
            (마크다운 "소개.md")
            (마크다운 "도메인.md")
            [:h2 "언어별 예제"]
            [:h3 "클로저 (Clojure)"]
            [:pre
             [:code "(함수 이용자-바인딩-미들웨어 [핸들러]
  (fn [요청]
    (바인딩 [*세션이용자* (세션이용자 요청)]
      (핸들러 요청))))"]]
            [:pre
             [:code "(use 'ring.middleware.content-type)

(def app
  (wrap-content-type
   your-handler
   {:mime-types {\"foo\" \"text/x-foo\"}}))"]]
            [:h3 "SQL 예제"]
            [:pre
             [:code "-- 프로젝트 테이블
CREATE TABLE 프로젝트 (
  소유자   VARCHAR(32) NOT NULL REFERENCES 이용자(아이디) ON DELETE CASCADE,
  이름     VARCHAR(64) NOT NULL,
  설명     VARCHAR(255) NOT NULL,
  공개     BOOLEAN NOT NULL DEFAULT TRUE,
  생성일시 TIMESTAMP NOT NULL DEFAULT NOW(),
  갱신일시 TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (소유자, 이름)
);"]]
            [:div#codemirror "이부분이 바뀌어야지요"]))
