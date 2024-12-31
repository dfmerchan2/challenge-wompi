Feature: Complete the payment process with PSE

  Scenario: Successfully make a payment through PSE
    Given that "Diego" has valid access tokens with status code 200
    When sending a payment request using "correct" information
    Then should see the status code 201
    And the transaction status is "PENDING"

  Scenario Outline: Making a payment through PSE with an "<optionAuthorization>" authorization
    Given that "Diego" has valid access tokens with status code 200
    When sending a payment request using "<data>" information
    Then should see the status code 401
    And should see an error message "<message>" indicating that the value of "AUTHORIZATION" is not valid
    Examples:
      | data                  | message                                                            |
      | invalid authorization | Llave no válida                                                    |
      | empty authorization   | Se esperaba una llave pública o privada pero no se recibió ninguna |

  Scenario Outline: Making a payment through PSE sending field "<field>" with value invalid
    Given that "Diego" has valid access tokens with status code 200
    When sending a payment request using "<data>" information
    Then should see the status code 422
    And should see an error message "<message>" indicating that the value of "<field>" is not valid
    Examples:
      | data                     | message                                                            | field            |
      | invalid acceptance token | El token de aceptación es inválido                                 | ACCEPTANCE_TOKEN |
      | empty acceptance token   | El token de aceptación está en un formato incorrecto               | ACCEPTANCE_TOKEN |
      | invalid amount           | El monto mínimo de una transacción es $1,500 exceptuando impuestos | AMOUNT           |
      | invalid currency         | Debe ser igual a COP                                               | CURRENCY         |
      | invalid signature        | La firma es inválida                                               | SIGNATURE        |
      | invalid email            | Email inválido                                                     | CUSTOMER_EMAIL   |
      | invalid user type        | Debe ser uno de estos: 0, 1                                        | USER_TYPE        |
      | invalid type document    | Debe ser uno de estos: RC, TI, CC, TE, CE, NIT, PP, DNI            | TYPE_DOCUMENT    |
      | invalid number document  | Debe ser completado                                                | NUMBER_DOCUMENT  |