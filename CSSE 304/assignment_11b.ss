(load "chez-init.ss"); This is a parser for simple Scheme expressions, such as those in EOPL, 3.1 thru 3.3.

; You will want to replace this with your parser that includes more expression types, more options for these types, and error-checking.

; Procedures to make the parser a little bit saner.

(define 1st car)
(define 2nd cadr)
(define 3rd caddr)

(define-datatype expression expression?
                 [var-exp        ; variable references
                     (id symbol?)]

                 ; literal expression
                 [lit-exp
                     (datum
                         (lambda (x)
                             (ormap
                                 (lambda (pred) (pred x))
                                 (list number? vector? boolean? symbol? string? pair? null?))))]

                 ; lambda
                 [lambdavar-exp
                     (datum symbol?)
                     (expr (list-of expression?))]

                 ; lambda
                 [lambda-exp
                     (datum (list-of expression?))
                     (expr (list-of expression?))]

                 ; let
                 [let-exp
                     (type symbol?)
                     (definitions (list-of expression?))
                     (use (list-of expression?))]

                 ; set
                 [set-exp
                     (to-define symbol?)
                     (to-set expression?)]

                 [app-exp        ; applications
                     (rator expression?)
                     (rands (list-of expression?))]
                 )

(define unparse-exp
    (lambda (datum)
        (cases expression datum
               (var-exp (v) v)
               (lit-exp (datum) datum)
               (lambda-exp (data expr) (apply list 'lambda (map unparse-exp data) (map unparse-exp expr)))
               (lambdavar-exp (var expr) (apply list 'lambda var (map unparse-exp expr)))
               (let-exp (type data expr) (apply list type (map unparse-exp data) (map unparse-exp expr)))
               (set-exp (data expr) (unparse-exp data) (unparse-exp expr))
               (app-exp (data expr) (apply list (unparse-exp data) (map unparse-exp expr))))))

(define parse-exp
    (lambda (datum)
        (cond
            [(symbol? datum) (var-exp datum)]
            [(number? datum) (lit-exp datum)]
            [(null? datum) (lit-exp datum)]
            [(vector? datum) (lit-exp datum)]
            [(pair? datum)
             (cond
                 [(not (list? datum)) (eopl:error 'parse-exp "~s is not a proper list" datum)]
                 [(eqv? (car datum) 'lambda)
                  (cond
                        [(< (length datum) 3) (eopl:error 'parse-exp "incorrect length: ~s" datum)]
                        [(symbol? (2nd datum)) (lambdavar-exp  (2nd datum) (map parse-exp (cddr datum)))]
                        [(not (andmap symbol? (2nd datum))) (eopl:error 'parse-exp "lambda argument list: formals must be symbols: " (2nd datum))]
                        [else (lambda-exp (map parse-exp (2nd datum)) (map parse-exp (cddr datum)))])]

                 [(eqv? (1st datum) 'if)
                  (cond
                      [(< (length datum) 3) (eopl:error 'parse-exp "if expression: should have (only) test, then, and else clauses: ~s" datum)]
                      [else (app-exp (parse-exp 'if) (map parse-exp (cdr datum)))])]

                 [(member (1st datum) (list 'let 'letrec 'let*))
                  (cond
                      [(< (length datum) 3) (eopl:error 'parse-exp "let expression: incorrect length: ~s" datum)]
                        [(not (list? (2nd datum))) (eopl:error 'parse-exp "not a proper list: ~s" (2nd datum))]
                        [(not (andmap list? (2nd datum))) (eopl:error 'parse-exp "not all proper lists: ~s" (2nd datum))]
                        [(not-correct-let-sizes? (2nd datum)) (eopl:error 'parse-exp "not all length 2: ~s" (2nd datum))]
                        [(not (andmap (lambda (x) (symbol? (car x))) (2nd datum))) (eopl:error 'parse-exp "first members must be symbols: ~s" (2nd datum))]
                        [else (let-exp (1st datum) (map parse-exp (2nd datum)) (map parse-exp (cddr datum)))])]

                 [(eqv? (1st datum) 'set!)
                  (cond
                      [(not (eq? (length datum) 3)) (eopl:error 'parse-exp "set!: Too many parts: ~s" (2nd datum))]
                        [else (begin (parse-exp (2nd datum)) (parse-exp (3rd datum)))])]

                 [else (app-exp (parse-exp (1st datum)) (map parse-exp (cdr datum)))])]
            [else (eopl:error 'parse-exp "bad expression: ~s" datum)])))


(define not-correct-let-sizes?
    (lambda (ls)
        (cond
            [(null? ls) #f]
            [(eq? (length (car ls)) 2) (not-correct-let-sizes? (cdr ls))]
            [else #t])))