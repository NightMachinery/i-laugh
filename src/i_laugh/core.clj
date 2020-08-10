(ns i-laugh.core
  (:use overtone.live))
(use 'overtone.osc)
(def client (osc-client "localhost" 9177))

(osc-send client "/curve-uniform" , "iG", (float 0.7) (float 0.1))
(osc-send client "/uniform" , "iR", (float 0.5))
(osc-send client "/growing-uniform" "iB" (float 100.0))
(osc-send client "/uniform" "iW" (float 100.0))
(osc-send client "/shader" "/Base/- Code/glsl/Fragment/shaderViewTest.frag")

(recording-start "/Base/- Art/Audio/i-laugh/trem_100_10000_100.wav")
(recording-stop)
(definst baz [freq 220] (-> (saw freq) (* 0.3)))
(definst trem [freq 440 depth 10 rate 6 ]
  (* 0.3
     (saw (max 10 (+ freq (* depth (sin-osc:ar rate)))))))
(trem 100 960 90)                       ;*
(trem 100 9999 75)                      ;**

(trem :freq 100 :depth 10000 :rate 100)
(trem 100 999 10)
(trem 5000 9999 30)
(trem 80 30 0.5)
(baz 440)
(baz 100)
(baz 1000)
(baz 600)
(baz 4)
(baz 3000)
(kill baz)
(stop)
