
Feature: Examples
  Optional description of the features

  Background:
    Given I set UserEmail Value in Data Scenario

  @test
  Scenario: Get Sites
    Given open web
    Then I load the DOM Information spoty_registro.json
    And I save text of Title as Scenario context
    And I set Email With Title.text

  @test
  Scenario: Get Site
    Given Local http:\\www.google.com.ar
    Then I load the DOM Information spoty_registro.json
    Then I close the app


  @tests
  Scenario: I do  a click
    Given open web
    Then I load the DOM Information spoty_registro.json
    And I do a click in element Email
    And I do write in Email this obedsilvestre339@gmail.com
    And I do a click in element confirm
    Then I close the app


  @test
  Scenario: Selects
    Given open web
    Then I load the DOM Information spoty_registro.json
    And I wait for element Mes de nacimiento to be present
    And I set text Febrero en dropdown Mes de nacimiento
    And I Set index 03 in dropdown Mes de nacimiento



  @test
  Scenario: I check state
    Given open web
    Then I load the DOM Information spoty_registro.json
    And I do a click in element Email
    And I do write in Email this obedsilvestre3393@gmail.com
    When click on the GeneroM
    When I uncheck the checkbox GeneroM
    Then I close the app



  @frames
  Scenario: Handle various funciones
    Given Local https://chercher.tech/practice/frames-example-selenium-webdriver
    Then I load the DOM Information frames.json
    And I switch to Frame: Frame2
    And I set text Avatar en dropdown Frame2 selector
    And I switch to Frame frame
    And I switch to Frame: Frame1
    And I do write in element Frame1 input this esto es una prueba
    Then I switch to Frame: Frame3


  @Check
  Scenario: checkss
    Given open web
    Then I load the DOM Information spoty_registro.json
    And click on the GeneroM
    And click on the check

  @test
  Scenario: Scroll to element js
    Given Local https://www.amazon.com/
    Then I load the DOM Information Amazon.json
   # And It other click Mi cuenta
    #este ( And I click in JS element Mi cuenta ) es un clcik de con javascritp este espera al elemento que carge
    #es diferente al normal ya que va mas corga junto con la pagina eso creo
    And I click in JS element Mi cuenta
    And I wait for element wait to be present


  @test
  Scenario: Scroll to on element js
    Given Local https://www.amazon.com/
    Then I load the DOM Information Amazon.json
    And  I scroll to element sobre amazon
    #And I click in JS element sobre amazon
    And I do a click in element sobre amazon
   # And I wait for element sobre amazon to be prensent


  @test
  Scenario: Open New Tab
    Given Local https://www.amazon.com/
    And I open new tab with URL https://chercher.tech/practice/frames-example-selenium-webdriver
    # And I switch to new windows esta linia lo que hace es estableserla como la principal para que se le puedan ejecutar lo
    #pasos de la siguiente pagina
    #And I switch to new windows
    And I go to practice window
    Then I load the DOM Information frames.json
    And I switch to Frame: Frame2
    And I set text Avatar en dropdown Frame2 selector
    And I go to Principal window
    And I open new tab with URL https://www.google.com/
    And I go to google window
    And I go to practice window
    And I go to Principal window
    Then I load the DOM Information Amazon.json
    And I click in JS element Mi cuenta
    And I wait for element wait to be present



  @test
  Scenario: Handle Alerts
    Given Local https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_alert
    Then I load the DOM Information frames.json
    And I switch to Frame: Frame alert
    And  I scroll to element but Alert
    And I wait 5 seconds
    Then I dismiss alerts


  @tests
    #esto es para hacer screenShots capturas de pantallas (And I take screenshot: HolyScreen) esa es la linia que pide el captur
  Scenario: take a screenshot
    Given open web
    And I wait site is loaded
    And I take screenshot: HolyScreen
    And I attach a Screenshot to Report : para Allure

  @tests
  Scenario: comprobando texto
    Given open web
    Then I load the DOM Information spoty_registro.json
    And I do a click in element Email
    And I do write in Email this obedsilvestre339@gmail.com
    And I do a click in element confirm
    #esta linea de abajo puede verificar una parte del texto vasta solo este una palabra o una sola letra
    Then Assert if Email error contains text Este correo electrónico ya está conectado a una cuenta.
    #este de aqui no tiene que estar completo
    Then Assert if Email error is equal to Este correo electrónico ya está conectado a una cuenta. Inicia sesión.
    Then I close the app


  @test
  Scenario: comprobando texto que no este
    Given open web
    Then I load the DOM Information spoty_registro.json
    And I do a click in element Email
    And I do write in Email this obedsilvestre339@gmail.com
    And I do a click in element confirm
    Then check Email error no is this text  ME LA CHUPA
    Then I close the app


  @test
  Scenario: visible element
    Given open web
    Then I load the DOM Information spoty_registro.json
    And I do a click in element Email
    Then Check if Email error NOT is Displayed
    And I do write in Email this obedsilvestre339@gmail.com
    And I do a click in element confirm
    Then Assert if Email error is Displayed
    And I wait site is loaded
    And I take screenshot: HolyScreen



    #esta prueva es mia
  @store
  Scenario: popula
    Given Local http://automationpractice.com/index.php
    Then I load the DOM Information Store.json
    And  I scroll to element popular
