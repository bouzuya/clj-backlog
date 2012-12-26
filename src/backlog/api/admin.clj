(ns backlog.api.admin
  [:require [backlog.util :as util]])

(defn get-users
  []
  (util/call :backlog.admin.getUsers))

; TODO: BAPI-47
(defn add-user
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-48
(defn update-user
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-49
(defn delete-user
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-50
(defn get-projects
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-51
(defn add-project
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-52
(defn update-project
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-53
(defn delete-project
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-54
(defn get-project-users
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-55
(defn add-project-user
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-56
(defn update-project-users
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-57
(defn delete-project-user
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-58
(defn add-custom-field
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-59
(defn updadte-custom-field
  []
  (throw (UnsupportedOperationException.)))

; TODO: BAPI-60
(defn delete-custom-field
  []
  (throw (UnsupportedOperationException.)))

