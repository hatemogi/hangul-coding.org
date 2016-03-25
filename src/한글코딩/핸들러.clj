(ns 한글코딩.핸들러
  (:use [미생.기본])
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes 앱라우트
  (GET "/" [] "안녕하세요")
  (route/not-found "찾을 수 없습니다."))

(def 앱
  (wrap-defaults 앱라우트 site-defaults))
