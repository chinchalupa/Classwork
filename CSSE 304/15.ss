;Assignment 15
;Nathan Blank

;Answer to Memoization Question
;Out implementation is slower than the implementation in the slides, because we do not internally
;memoize values. Our memoize has to call the function if there isn't a final value it has calculated, 
;while the other mainains all values up to the maximum call.

(define apply-continuation
 (lambda (k . v)
 (apply k v)))

;Problem 1
(define (member?-cps var ls k)
	(if (member var ls) (apply-continuation k #t)  (apply-continuation k #f)))

;b
(define set?-cps
	(lambda (ls k)
	(cond
		[(null? ls) (apply-continuation k #t)]
		[(not (pair? ls)) (apply-continuation k #f)]
		[(member?-cps (car ls) (cdr ls) (lambda (v) v)) (apply-continuation k #f)]
		[else (set?-cps (cdr ls) k)])))
;c
(define (1st-cps L k) (apply-continuation k (car L)))

(define set-of-cps ; removes duplicates to make a set
	(lambda (s k) 
	(cond [(null? s) (apply-continuation k '())]
	[(member (car s) (cdr s))
		;(set-of (cdr s))
		(set-of-cps (cdr s) (lambda (v) (apply-continuation k v)))]
	[else 
		;(cons (car s) (set-of (cdr s)))])))
		(set-of-cps (cdr s) (lambda (v) (apply-continuation k (cons (car s) v))))])))

(define domain-cps ; finds the domain of a relation.
	(lambda (rel k)
	 	(set-of-cps (map-cps 1st-cps rel identity) k)))

(define (map-cps fun-cps vals k)
	(cond
		[(null? vals) (apply-continuation k '())]
		[else (map-cps 
				fun-cps 
				(cdr vals) 
				(lambda (v) 
					(apply-continuation k (cons (fun-cps (car vals) identity) v)) ))]))

(define (identity v) v)

;e
(define (make-cps fun)
	(lambda (args k) (apply-continuation k (fun args))))

(define (andmap-cps fun-cps vals k)
		(cond
		[(null? vals) (apply-continuation k #t)]
		[else (if (fun-cps (car vals) (lambda (v) (if v #t #f)))
				(andmap-cps 
					fun-cps 
					(cdr vals)
					(lambda (v) 
						(apply-continuation k (and #t v) )) )
				(apply-continuation k #f))]))


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

(define sn-list-depth-cps (cps-snlist-recur 1
	(lambda (x y c)
		(apply-continuation c y))
	(lambda (x y c)
		(apply-continuation c (max (+ 1 x) y)))
))

(define (sn-list-reverse-cps ls k) ((cps-snlist-recur '() 
	(lambda (x y c)
		(apply-continuation c (append y (list x))))
	(lambda (x y c)
		(apply-continuation c (append y (list x))))
	) ls k)
)

(define (sn-list-occur-cps s ls k) ((cps-snlist-recur 0 
	(lambda (x y c)
		(apply-continuation c (+ (if (equal? s x) 1 0) y)))
	(lambda (x y c) 
	(apply-continuation c (+ x y))))
ls k))


(define (memoize fun hash equivalence)
	(let ([table (make-hashtable hash equivalence)]
		  )
	(lambda value 
		(if (hashtable-contains? table value) 
			(hashtable-ref table value 1) 
			(begin (hashtable-set! table value (apply fun value)) (hashtable-ref table value 1))
		 ))
	)
)


; (define (subst-leftmost new old ls proc)
;     (if (null? ls)
;         ls
;         (cond [(list? (car ls))	;Check if the value has been modified first, then keep going
;             (if (eq? (subst-leftmost new old (car ls) proc) (car ls))
;                 (apply list (car ls) (subst-leftmost new old (cdr ls) proc))
;                 (cons (subst-leftmost new old (car ls) proc) (cdr ls))
;             )]
;             [else (if (proc (car ls) old) ;check condition
;                 (cons new (cdr ls))
;                 (apply list (car ls) (subst-leftmost new old (cdr ls) proc))
;             )] 
;         )
;     )
; )



(define (subst-leftmost new old ls proc)
    (if (null? ls)
        ls
        (cond [(list? (car ls))	;Check if the value has been modified first, then keep going
            (if (call-with-values  (lambda () (values (subst-leftmost new old (car ls) proc) (car ls))) eq? )
                (call-with-values (lambda () (values (car ls) (subst-leftmost new old (cdr ls) proc))) cons)
                (call-with-values (lambda () (values (subst-leftmost new old (car ls) proc) (cdr ls))) cons)
            )]
            [else (if (proc (car ls) old) ;check condition
                (call-with-values (lambda () (values new (cdr ls))) cons)
                (call-with-values (lambda () (values (car ls) (subst-leftmost new old (cdr ls) proc))) cons)
            )] 
        )
    )
)
