(defproject hangul-coding.net "0.1.0-SNAPSHOT"
  :description "한글로 코딩하자"
  :url "https://hangul-coding.net"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.nrepl "0.2.12"]
                 [cider/cider-nrepl "0.11.0-SNAPSHOT"]
                 [org.clojure/tools.namespace "0.2.11"]
                 [misaeng "0.1.0"]
                 [compojure "1.5.0"]
                 [ring/ring-defaults "0.1.5"]
                 [hiccup "1.0.5"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-codox "0.9.4"]]
  :ring {:handler 한글코딩.핸들러/앱}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
