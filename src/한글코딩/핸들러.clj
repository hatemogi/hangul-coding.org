(ns 한글코딩.핸들러
  (:use [미생.기본]
        [한글코딩.뷰])
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes 앱라우트
  (GET "/" [] 첫페이지)
  (route/not-found "찾을 수 없습니다."))

(정의 앱
  (wrap-defaults 앱라우트 site-defaults))
