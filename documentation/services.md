# Services

## Spring REST API
Spring and Spring Boot are Java frameworks which provide a lot of out-of-the-box features for developing
REST-ful web services. We are using it here to tie together the website and the internal service which controls
the minecraft server.

This is done for several reasons. Firstly, it allows us to abstract the implementation of the minecraft behaviour 
by providing a standard REST API with endpoints and methods that follow convention.

## Vue Frontend
Vue.js is a frontend JavaScript framework. It is used to build SPA's (single-page-applications). Items on the webpage are
broken down into components and this allows for less code duplication.

## Minecraft Server
A simple Minecraft server is hosted alongside the other services. The minecraft server is a java process that allows players
to connect over a network. The server is the place where the generated structure will be placed. 

The Minecraft server we are using has the added benefit of being highly customisable. This means we are able to extend
the base functionality with custom plugins.

## Minecraft Server Plugin
The server plugin is an application that acts as a manager which controls the in-game world and places structures that 
are provided by the backend.