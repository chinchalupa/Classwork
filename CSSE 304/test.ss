(define product-cps
    (lambda (x y k)
        (if (null? y)
            (apply-continuation k '())
            (let loop ([x x])
                (if (null? x)
                    '()
                    (map-cps (lambda (s k) (apply-continuation k (list (car x) s)))
                             y
                             (lambda (map-result)
                                 (if (null? (cdr x))
                                     (apply-continuation k map-result)
                                     (append-cps map-result (loop (cdr x)) (lambda (append-result) (apply-continuation k append-result)))))))))))

;(define product-cps
;    (lambda (x y k)
;        (if (null? y)
;            (apply-continuation k '())
;            (let loop ([x x])
;                (if (null? x)
;                    (apply-continuation k ))
;                (append-cps (loop (cdr x) (append (map (lambda (s)
;                                                           (list (car x) s)) y)
;                                                  accum)))))))



(define apply-continuation
    (lambda (k . v)
        (apply k v)))


;#1 product-cps

; Provided code.  Do not modify it.

(define append-cps
    (lambda (l1 l2 k)
        (if (null? l1)
            (k l2)
            (append-cps (cdr l1)
                        l2
                        (lambda (appended-cdr)
                            (k (cons (car l1)
                                     appended-cdr)))))))



(define map-cps
    (lambda (proc-cps ls k)
        (if (null? ls)
            (k '())
            (proc-cps (car ls)
                      (lambda (proced-car)
                          (map-cps proc-cps (cdr ls)
                                   (lambda (mapped-cdr)
                                       (k (cons proced-car mapped-cdr)))))))))



(define list?-cps
    (lambda (ls k)
        (cond [(null? ls) (k #t)]
              [(not (pair? ls)) (k #f)]
              [else (list?-cps (cdr ls) k)])))



(define length-cps
    (lambda (ls k)
        (if (null? ls)
            (k 0)
            (length-cps (cdr ls)
                        (lambda (len)
                            (k (+ 1 len)))))))



(define matrix?-cps
    (lambda (m k)
        (list?-cps
            m
            (lambda (is-list?)
                (if (not is-list?)
                    (k #f)
                    (if (null? m)
                        (k #f)
                        (if (null? (car m))
                            (k #f)
                            (andmap-cps
                                list?-cps
                                m
                                (lambda (full-of-lists?)
                                    (if (not full-of-lists?)
                                        (k #f)
                                        (andmap-cps
                                            (lambda (L k)
                                                (length-cps
                                                    L
                                                    (lambda (length-L)
                                                        (length-cps
                                                            (car m)
                                                            (lambda (length-car)
                                                                (k (= length-L length-car)))))))
                                            (cdr m) k)))))))))))



(define andmap-cps
    (lambda (pred-cps ls k)
        (if (null? ls)
            (k #t)
            (pred-cps (car ls)
                      (lambda (v)
                          (if v
                              (andmap-cps pred-cps
                                          (cdr ls)
                                          k)
                              (k #f)))))))





(define (test-product-cps)
    (let ([correct '(
                        (((4 1) (4 2) (4 3) (3 1) (3 2) (3 3)))
                        (() ((4 1) (3 1)))
                        #f
                        #t
    )]

          [answers

              (list

                  (product-cps '(3 4) '(1 2 3) list)

                  (product-cps '(3 4) '(1)

                               (lambda (first-prod)

                                   (product-cps '() '(3 6 7)

                                                (lambda (second-prod)

                                                    (list second-prod first-prod)))))

                  (product-cps '(3 4) '(1 2 3) (lambda (v) (matrix?-cps v not)))

                  (product-cps '(3 4) '() (lambda (v) (matrix?-cps v not)))

                  )])

        (display 'correct)
        (display "\n")
        (display correct)
        (display "\n\n")
        (display answers)))
