
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="componentes/start.jsp" %>
<%@include file="componentes/header.jsp" %>

<section class="section" id="reservation">
        <div class="container">
            <div class="row">
               
                <div class="col-lg-10">
                    <div class="contact-form">
                        <form id="contact" action="svClientes" method="POST">
                          <div class="row">
                            <div class="col-lg-12">
                                <h4>Agregar Cliente</h4>
                            </div>
                            <div class="col-lg-6 col-sm-12">
                              <fieldset>
                                <input name="nombre" type="text" id="name" placeholder="Nombre*" required="">
                              </fieldset>
                            </div>
                            <div class="col-lg-6 col-sm-12">
                              <fieldset>
                              <input name="apellido" type="text" id="apellido"  placeholder="Apellido" required="">
                            </fieldset>
                            </div>    
                               <div class="col-lg-6 col-sm-12">
                              <fieldset>
                              <input name="dni" type="text" id="dni"  placeholder="Dni" required="">
                            </fieldset>
                            </div>
                            <div class="col-lg-6 col-sm-12">
                              <fieldset>
                                <input name="telefono" type="text" id="telefono" placeholder="Telefono*" required="">
                              </fieldset>
                            </div>
                           
                            <div class="col-lg-6">
                                <div id="filterDate2">    
                                  <div>
                                    <input  name="fechaNac" id="date" type="date" class="form-control" placeholder="Nacimiento">
                                    
                                  </div>
                                </div>   
                            </div>
                            <div class="col-md-6 col-sm-12">
                              <fieldset>
                                <select  name="genero" id="time">  
                                    <option name="genero" id="masculino">Masculino</option>
                                    <option name="genero" id="femenino">Femenino</option>                                   
                                </select>
                              </fieldset>
                            </div>
                           
                            <div class="col-lg-12">
                              <fieldset>
                                <button type="submit" id="form-submit" class="main-button-icon">Guardar</button>
                              </fieldset>
                            </div>
                          </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
<%@include file="componentes/end.jsp" %>