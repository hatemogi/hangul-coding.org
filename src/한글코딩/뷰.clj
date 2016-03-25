(ns 한글코딩.뷰
  (:use [미생.기본]
        [hiccup.core]
        [hiccup.page])
  (:require [ring.util.response]))

(함수 레이아웃 [속성 & 본문]
  (html5
   [:html {:lang "ko"}
    [:head
     [:title "제목"]
     (map include-css ["/css/milligram.min.css"
                       "/css/한글코딩.css"])]
    [:body
     [:nav]
     (into [:main] 본문)
     [:footer "라이선스"]
     (map include-js ["/js/한글코딩.js"])]]))

(함수 첫페이지 [요청]
  (레이아웃 {:제목 "페이지 제목"}
            [:div "안녕하세요"]))
