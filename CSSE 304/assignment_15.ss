; Problem 1
(define member?-cps
    (lambda (val ls k)
        (cond [(member val ls) (apply-continuation k #t)]
              [else (apply-continuation k #f)])))

; Problem 2
(define set?-cps
    (lambda (ls k)
        (cond
            [(null? ls) (apply-continuation k #t)]
            [(not (pair? ls)) (apply-continuation k #f)]
            [else (member?-cps (car ls) (cdr ls)
                               (lambda (x)
                                   (if x (apply-continuation k #f) (set?-cps (cdr ls) (lambda (y) (apply-continuation k y))))))])))

(define 1st car)

(define set-of-cps
    (lambda (ls k)

        (cond [(null? ls) (apply-continuation k '())]
              [else (member?-cps (car ls) (cdr ls)
                                 (lambda (x)
                                     (if x
                                         (set-of-cps (cdr ls)
                                                     (lambda (y) (apply-continuation k y)))
                                         (set-of-cps (cdr ls)
                                                     (lambda (z) (apply-continuation k (cons (car ls) z)))))))])))

(define 1st-cps
          (lambda (x k)
              (apply-continuation k (car x))))

(define map-cps
    (lambda (proc ls k)
        (cond [(null? ls) (apply-continuation k '())]
              [else (map-cps proc (cdr ls)
                             (lambda (x)
                                 (apply-continuation k (cons (proc (car ls) (lambda (y) y)) x))))])))

(define make-cps
    (lambda (proc)
        (lambda (x k) (apply-continuation k (proc x)))))

(define domain-cps ; finds the domain of a relation.
    (lambda (rel k)
        (set-of-cps (map-cps 1st-cps rel (lambda (x) x)) k)))

;(define domain-cps
;    (lambda (rel k)
;        (set-of-cps rel (lambda (x) (map-cps car rel (lambda (y) (apply-continuation k y)))))))

(define andmap-cps
    (lambda (proc ls k)
        (cond [(null? ls) (apply-continuation k #t)]
              [else (andmap-cps proc (cdr ls) (lambda (x)
                                                  (if (not (car ls) )
                                                      (apply-continuation k #f)
                                                      (apply-continuation k (and (proc (car ls) (lambda (y) y)) x)))))])))

(define snlist-recur-cps
    (lambda (seed item-proc list-proc)
        (letrec ([helper
                     (lambda (ls k)
                         (if (null? ls)
                             (apply-continuation k seed)
                             (let ([c (car ls)])
                                 (if (or (pair? c) (null? c))
                                     (list-proc (helper c (lambda (x) (apply-continuation k x))) (helper (cdr ls) (lambda (x) (apply-continuation k x))))
                                     (item-proc c (helper (cdr ls) (lambda (x) (apply-continuation k x))))))))])
            helper)))


(define apply-continuation
    (lambda (k . v)
        (apply k v)))



(define +-cps
         (lambda (a b k)
             (apply-continuation k (+ a b))))

(define (cps-snlist-recur base-value slist-proc-val slist-proc-list)
    (letrec
            ([helper
                 (lambda (ls k)
                     (cond [(null? ls) (apply-continuation k base-value)]
                           [(list? (car ls)) (helper (cdr ls) (lambda (v)
                                                                  (helper (car ls) (lambda (v2)
                                                                                       (slist-proc-list v2 v (lambda (v3)
                                                                                                                 (apply-continuation k v3))))))) ]
                           [else (helper (cdr ls) (lambda (v)
                                                      (slist-proc-val (car ls) v (lambda (v2)
                                                                                     (apply-continuation k v2))))) ]
                           ))])
        helper))

; (define (sn-list-depth-cps ls k) ((cps-snlist-recur 1
; 	(lambda (x y c)
; 		(apply-continuation c y))
; 	(lambda (x y c)
; 		(apply-continuation c (max (+ 1 x) y)))
;) ls k))

(define (sn-list-depth-cps ls k)
    ((cps-snlist-recur 1
    (lambda (f s k2)
        (apply-continuation k2 s))
    (lambda (f s k2)
        (apply-continuation k2 (max (+ 1 f) s)))) ls k))

(define (sn-list-reverse-cps ls k)
    ((cps-snlist-recur '()
      (lambda (f s k2)
          (apply-continuation k2 (append s (list f))))
      (lambda (f s k2)
          (apply-continuation k2 (append s (list f))))) ls k))

(define (sn-list-occur-cps val ls k) ((cps-snlist-recur 0
      (lambda (f s k2)
          (apply-continuation k2 (+ (if (equal? val f) 1 0) s)))
      (lambda (f s k2)
          (apply-continuation k2 (+ s f)))) ls k))


(define memoize
    (lambda (fun hash equivalence)
        (let ([table (make-hashtable hash equivalence)])
            (lambda value
                (if (hashtable-contains? table value)
                    (hashtable-ref table value 1)
                    (begin (hashtable-set! table value (apply fun value)) (hashtable-ref table value 1)))))))


(define subst-leftmost
    (lambda (new old ls proc)
    (if (null? ls)
        ls
        (cond [(list? (car ls))	;Check if the value has been modified first, then keep going
               (if (call-with-values  (lambda () (values (subst-leftmost new old (car ls) proc) (car ls))) eq?)
                   (call-with-values (lambda () (values (car ls) (subst-leftmost new old (cdr ls) proc))) cons)
                   (call-with-values (lambda () (values (subst-leftmost new old (car ls) proc) (cdr ls))) cons))]
              [else (if (proc (car ls) old) ;check condition
                        (call-with-values (lambda () (values new (cdr ls))) cons)
                        (call-with-values (lambda () (values (car ls) (subst-leftmost new old (cdr ls) proc))) cons))]))))