(ns 한글코딩.핸들러-검사
  (:use [미생.기본]
        [미생.검사]
        [한글코딩.핸들러])
  (:require [ring.mock.request :as mock]))

(검사정의 앱검사
  (검사 "main route"
    (가정 [응답 (앱 (mock/request :get "/"))]
      (확인 (= (:status 응답) 200))
      (확인 (= (:body 응답) "안녕하세요"))))

  (검사 "not-found route"
    (가정 [응답 (앱 (mock/request :get "/invalid"))]
      (확인 (= (:status 응답) 404)))))
