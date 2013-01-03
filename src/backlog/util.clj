(ns backlog.util
  [:require
   [backlog.config :as config]
   [backlog.case :as case]
   [clj-http.client :as http]
   [necessary-evil.core :as xml-rpc]
   [necessary-evil.methodcall :as mc]
   [necessary-evil.methodresponse :as mr]
   [necessary-evil.xml-utils :as xu]])

;;; FIXME: this code is necessary-evil's code.
(defn call
  [method-name & args]
  (io! "XML-RPC in transaction"
   (let [xml (mc/unparse (mc/methodcall method-name args))
         unparsed (if args
                    xml
                    (assoc xml :content
                         (conj (xml :content)
                               {:tag :params :attrs nil :content []})))
         body (-> unparsed xu/emit with-out-str)
         auth config/*backlog-auth*
         post-params {:content-type "text/xml;charset=UTF-8"
                      :basic-auth [(auth :username) (auth :password)]}
         endpoint-url (str "https://" (auth :spacename) ".backlog.jp/XML-RPC")
         response (http/post endpoint-url (assoc post-params :body body))]
     (-> response :body xu/to-xml mr/parse))))

(def backlog-snake-case-keywords
  #{:user-id
    :password-md5
    :mail-address
    :mail-setting
    :use-chart
    :start-date
    :due-date
    :estimated-hours
    :actual-hours
    :created-on
    :created-on-min
    :created-on-max
    :updated-on
    :updated-on-min
    :updated-on-max
    :start-date-min
    :start-date-max
    :due-date-min
    :due-date-max
    :substitute-id
    :custom-fields})

(defn backlog-keyword
  [k]
  (let [normalized (case/normalized-keyword k)
        normalize (if (backlog-snake-case-keywords normalized)
                    case/snake-keyword
                    case/camel-keyword)]
    (normalize k)))

