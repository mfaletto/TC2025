grammar compiladores;

@header {
package compiladores;
}

fragment LETRA : [A-Za-z] ;
fragment DIGITO : [0-9] ;

NUMERO : DIGITO+ ;
COMILLA : '\'';
INT : 'int';
DOUBLE : 'double';
VOID : 'void';
CHAR : 'char';
PYC : ';' ;
IGUAL : '=';
COMA : ',';
PA : '(' ;
PB : ')' ;
LA : '{' ;
LB : '}' ;
IF : 'if';
ELSE : 'else';
WHILE : 'while';
FOR : 'for';
RETURN : 'return';
PUNTO : '.';
FLOTANTE: NUMERO PUNTO NUMERO;
CARACTER: COMILLA LETRA+ COMILLA;

ADD_OP : '+' ;
SUB_OP : '-' ;
MUL_OP : '*' ;
DIV_OP : '/' ;
MOD_OP : '%' ;
AND_OP : '&&' ;
OR_OP : '||' ;
NOT_OP : '!' ;
EQ_OP : '==' ;
NEQ_OP : '!=' ;
LT_OP : '<' ;
GT_OP : '>' ;
LTE_OP : '<=' ;
GTE_OP : '>=' ;
INC_OP : '++' ;
DEC_OP : '--' ;

ID : (LETRA | '_')(LETRA | DIGITO | '_')* ;
WS: [ \t\r\n]+ -> skip;

programa : instrucciones EOF;

instrucciones : instruccion instrucciones
              |
              ;

instruccion : declaracion
            | asignacion
            | expresion PYC
            | while
            | if
            | for
            | llamada_funcion PYC
            | bloque
            | retorno
            ;

parametros : parametro mas_parametros
           |
           ;

parametro : tipo ID ;

mas_parametros : COMA parametro mas_parametros
               |
               ;

declaracion : parametros declaracion_continua ;

declaracion_continua : PYC
                     | IGUAL expresion PYC
                     | asignacion_continua
                     | PA parametros PB bloque
                     | PA parametros PB PYC
                     | COMA ID declaracion
                     ;


tipo : INT | DOUBLE | VOID | CHAR ;

asignacion: ID asignacion_continua;

asignacion_continua : IGUAL expresion mas_asignaciones
                    | (ADD_OP | SUB_OP | MUL_OP | DIV_OP | MOD_OP) IGUAL expresion mas_asignaciones
                    | INC_OP mas_asignaciones
                    | DEC_OP mas_asignaciones
                    ;


mas_asignaciones: PYC
                | COMA asignacion
                | asignacion_continua
                | PB
                ;

expresion : oal op_expresion
          ;

CADENA : '"' (~["\\] | '\\' .)* '"' ;

op_expresion : (ADD_OP | SUB_OP | MUL_OP | DIV_OP | MOD_OP | AND_OP | OR_OP | EQ_OP | NEQ_OP | LT_OP | GT_OP | LTE_OP | GTE_OP) oal op_expresion
             |
             ;

oal : NUMERO
    | FLOTANTE
    | ID
    | CARACTER
    | llamada_funcion
    | PA expresion PB
    ;


llamada_funcion : ID PA argumentos PB ;

argumentos : expresion mas_argumentos
           |
           ;

mas_argumentos : COMA argumentos
               |
               ;

bloque : LA instrucciones LB ;

if : IF PA expresion PB bloque else_bloque ;

else_bloque : ELSE bloque
            |
            ;

while : WHILE PA expresion PB bloque ;

for : FOR PA inicializacion condicion PYC actualizacion bloque ;

inicializacion : declaracion
               | asignacion
               |
               ;

condicion : expresion
          |
          ;

actualizacion : asignacion
              |
              ;


retorno : RETURN expresion PYC
        | RETURN PYC
        ;