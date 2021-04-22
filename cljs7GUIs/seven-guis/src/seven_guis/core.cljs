(ns seven-guis.core
    (:require
      [reagent.core :as r]
      [reagent.dom :as d]))

;; -------------------------
;; Views



(def click-count (r/atom 1330))
(defn counter []
   [:div 
    [:input {:type "text" :value @click-count :readonly true }]
    [:input {:type "button" :value "Count" :on-click (fn [e] 
                      (swap! click-count inc))}]]
   )

(defn home-page []
  [:div [:h2 "Counter"] 
  [counter]
  ])


;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [home-page] (.getElementById js/document "app")))

(defn ^:export init! []
  (mount-root))
