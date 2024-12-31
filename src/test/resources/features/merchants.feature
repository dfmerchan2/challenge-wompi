Feature: Generate the merchants

  Scenario: Get successful merchants
    When that "Diego" requests a list of merchants using the "valid" authentication key
    Then the status code should be 200
    And verify that the correct information is returned

  Scenario: Get the merchants with invalid format
    When that "Diego" requests a list of merchants using the "invalid format" authentication key
    Then the status code should be 422
    And the response should include type of error "INPUT_VALIDATION_ERROR" and message "Formato inválido"

  Scenario Outline: Get the merchants with invalid authentication key
    When that "Diego" requests a list of merchants using the "<merchantsOption>" authentication key
    Then the status code should be <statusCode>
    And the response should include type of error "<type>" and reason "<reason>"
    Examples:
      | merchantsOption | statusCode | type                 | reason                                                             |
      | invalid         | 404        | NOT_FOUND_ERROR      | La entidad solicitada no existe                                    |
      | empty           | 401        | INVALID_ACCESS_TOKEN | Se esperaba una llave pública o privada pero no se recibió ninguna |