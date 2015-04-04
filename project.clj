(defproject assistant "0.1.0-SNAPSHOT"
  :description "A super awesome extensible personal assistant"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.7.0-alpha6"]
                 [org.clojure/clojurescript "0.0-3169"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [cljs-http "0.1.30"]
                 [com.cognitect/transit-cljs "0.8.205"]
                 [markdown-clj "0.9.65" :exclusions [org.clojure/clojure]]
                 [domina "1.0.3"]
                 [garden "1.2.5"]
                 [prismatic/dommy "1.0.0"]
                 [hickory "0.5.4"]
                 [org.omcljs/om "0.8.8"]
                 [reagent "0.5.0"]]

  :plugins [[lein-cljsbuild "1.0.5"]
            [lein-node-webkit-build "0.1.7-SNAPSHOT" :exclusions [[org.apache.commons/commons-compress]
                                                         [com.fasterxml.jackson.core/jackson-databind]
                                                         [com.fasterxml.jackson.core/jackson-annotations]]]]

  :source-paths ["src"]

  :node-webkit-build {:root "./public"
                      :name "Assistant"
                      :osx {:icon "./public/images/icon.icns"}
                      :platforms #{:osx64}
                      :disable-developer-toolbar true
                      :nw-version :latest}

  :cljsbuild {
    :builds [{:id "assistant"
              :source-paths ["src" "plugins"]
              :compiler {
                         :output-to "public/assistant.js"
                         :output-dir "public/out"
                         :source-map true
                         :optimizations :none}}]})
