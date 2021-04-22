(ns seven-guis.core
    (:require
      [reagent.core :as r]
      [reagent.dom :as d]))

;; -------------------------
;; Views

(def todos (r/atom [
  {:desc "garlic" :completed true}
  {:desc "tomato" :completed true}
  {:desc "pasta" :completed false}]))

(defn todo-form []
  (let [new-item (r/atom "")] 
  (fn []
    [:form {:on-submit (fn [e]
                          (.preventDefault e)
                          (swap! todos conj {:completed "false" :desc @new-item})
                          (reset! new-item "")
                          )}
      [:input {
              :type "text" 
              :placeholder "Add a new item"
              :on-change (fn [e] 
                          (reset! new-item (.-value (.-target e))))}]])))
    

(defn todo-item [todo] 
  [:li {:style {:color (if (:completed todo) "green" "red")}} (:desc todo)])

(defn home-page []
  [:div [:h2 "List"] 
  [:p "Add a new item below"]
  [todo-form]
  [:ul
  (for [todo @todos]
    (todo-item todo)) 
  ]])


;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [home-page] (.getElementById js/document "app")))

(defn ^:export init! []
  (mount-root))
