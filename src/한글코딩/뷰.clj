(ns 한글코딩.뷰
  (:use [미생.기본]
        [hiccup.core]
        [hiccup.page])
  (:require [hiccup.util :refer [escape-html]]
            [clojure.string :as s]
            [ring.util.response :as 응답]))

(함수- 머리말 []
  (가정 [링크 (fn [주제]
                (str "/" (s/replace 주제 #"\s" "-") ".html"))]
    [:nav.머리말
     [:section.container
      [:a.제목 {:href "/"} [:h1.제목 "한글코딩"]]
      [:ul.머리말-목록.float-right
       (for [주제 ["언어"
                   "도구"
                   "유니코드"
                   "소통"]]
         [:li.목록-링크 [:a {:href (링크 주제)} 주제]])]]]))

(함수- 꼬리말 []
  (가정 [링크 (fn [주소 텍스트]
                [:li.목록-링크 [:a {:href 주소} 텍스트]])]
    [:footer.꼬리말
     [:section.container
      [:ul.꼬리말-목록
       (링크 "/도와주기.html" "도와주기")]
      [:ul.꼬리말-목록.float-right
       (링크 "/작성자.html" "2016년 김대현")]]]))

(함수- GA [코드]
  [:script
   "(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', '" 코드 "', 'auto');
  ga('send', 'pageview');"])

(함수 레이아웃 [속성 & 본문]
  (가정 [타이틀 (str "한글코딩" (만약-가정 [제목 (:제목 속성)] (str " | " 제목)))
         설명 "개발자는 평소 한국어로 말하고 듣고 한글을 읽고 쓰며 지냅니다. 그런데 왜 소스코드를 작성할 때는 영문만 쓰는 걸까요? 한국말로 말하듯 한글로 글 쓰듯, 한글로 코딩해 봅시다. 개발하기 한결 쉬워집니다."]
    (html5 {:lang "ko"}
           [:head
            [:meta {:charset "utf-8"}]
            [:meta {:content "IE=edge" :http-equiv "X-UA-Compatible"}]
            [:meta { :name "description" :content 설명}]
            [:meta {:name "author" :content "https://medium.com/@hatemogi"}]
            [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0, minimum-scale=0.7"}]

            [:meta {:name "og:site_name" :content "한글코딩"}]
            [:meta {:name "og:url" :content "http://한글코딩.org/"}]
            [:meta {:name "og:title" :content 타이틀}]
            [:meta {:name "og:locale" :content "ko_KR"}]
            [:meta {:name "og:description" :content 설명}]

            [:meta {:name "twitter:card" :content "summary"}]
            [:meta {:name "twitter:site" :content "@hangulcoding"}]
            [:meta {:name "twitter:title" :content 타이틀}]
            [:meta {:name "twitter:description" :content 설명}]

            [:title 타이틀]
            (map include-css ["https://fonts.googleapis.com/css?family=Roboto"
                              "http://fonts.googleapis.com/earlyaccess/nanumgothiccoding.css"
                              "묶음.css"])]
           [:body
            [:div.포장
             (머리말)
             (into [:main.container.본문] 본문)
             (꼬리말)]
            (map include-js ["묶음.js"])
            (GA "UA-75606874-1")])))

(함수 리모트마크다운 [파일명]
  [:div.마크다운 {:data-remote 파일명 :data-type "마크다운" :data-option "신뢰"}])

(함수 마크다운 [파일명]
  [:div.마크다운 {:data-markdown true :data-option "신뢰"}
   (-> (str "resources/public" 파일명) slurp escape-html)])

(함수 하이라이트 [파일명]
  [:div.소스 {:data-remote 파일명 :data-type "소스코드"}])

(함수 첫페이지 [요청]
  (레이아웃 {:제목 "소개"}
            [:section (리모트마크다운 "/md/소개.md")]
            [:section (리모트마크다운 "/md/공감.md")]))

(함수 소통 [요청]
  (레이아웃 {:제목 "소통"
             :스크립트 []}
            [:section
             [:h1 "사람들의 이야기"]]
            [:section
             [:h2 "트위터 타임라인"]
             [:a.twitter-timeline
              {:href "https://twitter.com/hashtag/%ED%95%9C%EA%B8%80%EC%BD%94%EB%94%A9"
               :data-widget-id "715893526781829120" :data-dnt true :lang "ko" :data-lang "ko"}
              "#한글코딩 관련 트윗"]
             [:script
              "!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document,'script','twitter-wjs');"]]
            [:section
             [:h2 "페이스북 페이지"]
             [:ul [:li [:a {:href "https://facebook.com/hangulcoding"} "https://facebook.com/hangulcoding"] ]]]
            [:section
             [:h2 "GitHub 페이지"]
             [:ul [:li [:a {:href "https://github.com/hatemogi/hangul-coding.org"} "hatemogi/hangul-coding.org"] ]]
             ]))

(함수 찾을수없어요 [요청]
  (레이아웃 {}
            [:h1 "찾을 수 없습니다"]
            [:section
             [:a.button {:href "/"} "첫페이지로 가기"]]))
