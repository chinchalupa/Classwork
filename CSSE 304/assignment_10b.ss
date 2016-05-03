(define tracking '())

; Problem 1
(define lexical-address
    (lambda (ls)
        (lexical-helper ls (list 'if 'lambda 'let 'set!) tracking)
    )
)

; Helper problem 1
(define lexical-helper
    (lambda (ls notlexical tracking)
        (if (null? ls)
            '()
            (if (list? ls)
                (let ([first (car ls)])
                    (if (list? first)
                        (cons (lexical-helper first notlexical tracking) (lexical-helper (cdr ls) notlexical tracking))
                        (if (member first notlexical)
                            (if (equal? first 'lambda)
                                (list first (cadr ls) (lexical-helper (caddr ls) notlexical (add-to-tracking-single (cadr ls) (increment-tracking-depth tracking) 0)))
                                (if (equal? first 'let)
                                    (list first (map (lambda (x) (list (car x) (car (lexical-helper (cdr x) notlexical tracking)))) (cadr ls))
                                          (lexical-helper (caddr ls) notlexical (add-to-tracking-single (map car (cadr ls)) (increment-tracking-depth tracking) 0)))
                                    (cons first (lexical-helper (cdr ls) notlexical tracking))
                                )
                            )
                            (cons (get-tracking-variable-data first tracking) (lexical-helper (cdr ls) notlexical tracking)))
                    )
                )
                (get-tracking-variable-data ls tracking)
            )
        )
    )
)


; Tracking in format (var depth pos)
(define add-to-tracking-single
    (lambda (ls tracking inc)
        (if (null? ls)
            tracking
            (add-to-tracking-single (cdr ls) (cons (list (car ls) 0 inc) tracking) (+ 1 inc)))))

; Helper for incrementing depth
(define increment-tracking-depth
    (lambda (tracking)
        (map (lambda (x) (list (car x) (+ (cadr x) 1) (caddr x))) tracking)))

; Get the tracking data
(define get-tracking-variable-data
    (lambda (var tracking)
        (if (null? tracking)
            (list ': 'free var)
            (let ([first (car tracking)])
                (if (equal? var (car first))
                    (list ': (cadr first) (caddr first))
                    (get-tracking-variable-data var (cdr tracking)))))))

(define reverse-tracking-variable-data
    (lambda (depth var tracking)
        (if (null? tracking)
            '()
            (let ([first (car tracking)])
                (if (and (eq? (cadr first) depth) (eq? (caddr first) var))
                    (car first)
                    (reverse-tracking-variable-data depth var (cdr tracking)))))))

; Problem 2
(define un-lexical-address
    (lambda (ls)
        (un-lexical-address-helper ls (list 'if 'lambda 'set! 'let) tracking)))

(define un-lexical-address-helper
    (lambda (ls not-lexical tracking)
        (if (null? ls)
            '()
            (if (list? ls)
              (let ([first (car ls)])
                  (if (eq? first ':)
                      (if (eq? (cadr ls) ))
                      ))))))



