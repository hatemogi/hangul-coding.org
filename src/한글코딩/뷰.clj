(ns 한글코딩.뷰
  (:use [미생.기본]
        [hiccup.core]
        [hiccup.page])
  (:require [ring.util.response]))

(함수- 머리말 []
  [:nav.머리말
   [:section.container
    [:a.제목 {:href "/"} [:h1.제목 "한글코딩.org"]]
    [:ul.머리말-목록.float-right
     (for [링크 ["공감?"
                 "인코딩!"
                 "언어*"
                 "도구 및 설정+"]]
       [:li.머리말-목록-링크 [:a {:href (str "/" 링크)} 링크]])]]])

(함수- 꼬리말 []
  [:footer.꼬리말
   [:section.container
    [:ul.꼬리말-목록
     [:li.꼬리말-목록-링크 [:a {:href "/고마운분들"} "고마운 분들"]]
     [:li.꼬리말-목록-링크 [:a {:href "/돕는방법"} "돕는 방법"]]]
    [:ul.꼬리말-목록.float-right
     [:li.꼬리말-목록-링크 [:a {:href "/작성자"} "2016년 김대현 올림"]]]]])

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
     [:meta {:charset "utf-8"}]
     [:meta {:content "IE=edge" :http-equiv "X-UA-Compatible"}]
     [:meta {:content "한글로 코딩합시다" :name "description"}]
     [:meta {:content "https://keybase.io/hatemogi" :name "author"}]
     [:meta {:content "width=device-width, initial-scale=1.0, minimum-scale=1.0"
       :name "viewport"}]
     [:title "한글코딩"]
     (map include-css ["/css/normalize.css"
                       "/css/milligram.min.css"
                       "/css/codemirror.css"
                       "/css/색/github.css"
                       "/css/theme/neat.css"
                       "/css/theme/neo.css"
                       "/css/theme/base16-light.css"
                       "/css/theme/cobalt.css"
                       "/css/theme/ttcn.css"
                       "/css/한글코딩.css"])]
    [:body
     [:div.포장
      (머리말)
      (into [:main.container] 본문)
      (꼬리말)]
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
  [:div.마크다운 {:data-markdown 파일명}])

(함수 소스코드 [파일명]
  [:pre.소스 [:code {:data-src 파일명}]])

(함수 첫페이지 [요청]
  (레이아웃 {:제목 "페이지 제목"}
            [:section (마크다운 "소개.md")]
            [:section (마크다운 "공감.md")]
            [:h2 "언어별 예제"]
            [:h3 "SQL 예제"]
            (소스코드 "sql/프로젝트.sql")))
