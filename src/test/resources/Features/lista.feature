Feature: Examples
  Optional description of the features


@frames
Scenario: Handle various funciones
  Given Local https://docs.google.com/forms/d/e/1FAIpQLSeqkj5Px9ZKM4dHB7PpPXx7CJwG5UY0MCVgBOdp4GCvFX8HhA/viewform
  Then I load the DOM Information listajson.json
  And I do write in codigoCole this 09922
  And  I do a click in element Selecione_Distrito
  And  I do a click in element Distrito_Selecion
  And I do write in Telefono_del_centro this 809-598-8371
  And I do write in Correo_del_centro this aoscolegiomisprimeros@gmail.com
  And I do write in Direccion_del_centro this Fausto Cejas Rodrigues,69,Barrio
  And  I do a click in element Sector_privado
  And  I do a click in element selecion_tanda
  And  I do a click in element matutina_selecion
  And  I do a click in element selecion_modalidad
  And  I do a click in element modalidad_academica
  And  I do a click in element niveles_educativo_inical
  And  I do a click in element niveles_educativo_primario
  And I do write in nombre_apellido_diretor this Mirtha Santana
  And I do write in cell_diretor this 829-865-2753
  And I do write in gmail_director this santanamirtha715@gmail.com
  And  I do a click in element siguiente_pagina2
  And I wait 20 seconds
  And I do write in total_de_femeninos_nivel_inicial this 3
  And I do write in total_de_masculino_nivel_inicial this 4
  And I do write in total_de_femeninos_nivel_primario this 3
  And  I do a click in element siguiente_pagina3
  And I wait 20 seconds
  And  I do a click in element Estudiantes_participando_educación_distancia
  And  I do a click in element siguiente_pagina4
  And I wait 20 seconds
  And  I do a click in element selecione_modalidad_distancia
  And I do write in TotalEstudiantes_Femenino_Nivel_Inicial_participando_clases this 3
  And I do write in estudiante_femeninas_que_no_asitieron_inicial this 1
  And I do write in TotalEstudiantes_masculino_Nivel_Inicial_participando_clases this 4
  And I do write in estudiante_masculino_que_no_asitieron_inical this 2
  And I do write in TotalEstudiantes_Femenino_Nivel_primaria_participando_clases this 3
  And I do write in estudiante_femenino_que_no_asitieron_primario this 1
  And I do write in TotalEstudiantes_masculino_Nivel_primaria_participando_clases this 4
  And I do write in estudiante_masculino_que_no_asitieron_primario this 1
  And  I do a click in element siguiente_pagina5
  And I wait 20 seconds
  And I do write in (Total Estudiantes Femenino Nivel Inicial que nunca ha participando en clases) this 0
  And I do write in (Total Estudiantes masculino Nivel Inicial que nunca han participando en clases) this 0
  And I do write in (Total Estudiantes femenino, Nivel Primario, que no han participando en clases) this 0
  And I do write in (Total Estudiantes masculino del Nivel Primario, que no han participando en clases) this 0
  And  I do a click in element FinEnviar









