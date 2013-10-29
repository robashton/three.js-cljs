(ns cljssphere.core) 
(def scene-width 400)
(def scene-height 300)
(def view-angle 45)
(def aspect (/ scene-width scene-height))
(def near 0.1)
(def far 10000)

(def renderer (THREE.WebGLRenderer.))
(def camera (THREE/PerspectiveCamera. view-angle aspect near far))
(def scene (THREE/Scene.))
(.add scene camera)
(.set (.-position camera) 0 0 300)
(.setSize renderer scene-width scene-height)

(def radius 50)
(def segments 16)
(def rings 16)

(def sphere (THREE.Mesh. 
              (THREE.SphereGeometry. radius segments rings)
              (THREE.MeshPhongMaterial. (clj->js { "color" 0xff0000}))))


(def sphere2 (THREE.Mesh. 
              (THREE.SphereGeometry. radius segments rings)
              (THREE.MeshPhongMaterial. (clj->js { "color" 0xff0000}))))

(def sphere3 (THREE.Mesh. 
              (THREE.SphereGeometry. radius segments rings)
              (THREE.MeshPhongMaterial. (clj->js { "color" 0xff0000}))))

(.set (.-position sphere) 10 0 0)
(.set (.-position sphere2) -20 0 0)
(.set (.-position sphere3) 60 0 0)

(.add scene sphere)
(.add scene sphere2)
(.add scene sphere3)



(def light (THREE.PointLight. 0xFFFF00))

(.set (.-position light) 10 50 130)
(.add scene light)

(.appendChild (.getElementById js/document "container") (.-domElement renderer))

(def x (atom 10))
(def y (atom 51))
(def z (atom 130))
(def t (atom 0))

(js/setInterval (fn [] 
                  (.render renderer scene camera)
                  (.set (.-position light) @x @y @z)
                  (swap! x (fn [i] (+ 10 (* 100 (Math.sin @t)))))
                  (swap! z (fn [i] (+ 130 (* 100 (Math.cos @t)))))
                  (swap! t (fn [i] (+ i 0.05)))
                  ) 30)
