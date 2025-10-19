Spring Boot AWS Cognito Authentication Demo

This repository demonstrates a simple user authentication flow using AWS Cognito with a Spring Boot backend and a static frontend for signup and login.

Overview

The project showcases how to integrate AWS Cognito User Pools with a Spring Boot application to manage user authentication securely. The frontend is a static web interface that interacts with the backend to register new users and allow existing users to log in.

Features

User signup with AWS Cognito User Pool

User login with authentication against Cognito

Secure token management (ID Token, Access Token, Refresh Token)

Backend validation of tokens using AWS Cognito SDK

Demonstration of ADMIN_NO_SRP_AUTH flow for login

Simple static frontend (HTML/JavaScript) for user interaction

Technologies Used

Spring Boot – REST API backend for authentication logic

AWS Cognito User Pools – Managed user directory and authentication provider

AWS SDK for Java – Interact with AWS Cognito service

Static HTML/CSS/JavaScript – Frontend for signup and login forms

PowerShell/AWS CLI – Example scripts for user management and secret hash generation

Getting Started
Prerequisites

AWS account with Cognito User Pool configured

AWS CLI installed and configured

Java 11+ and Maven or Gradle

PowerShell or terminal for running AWS CLI commands

Setup

Clone the repository

Configure your AWS Cognito User Pool ID, Client ID, and Client Secret in the Spring Boot application properties

Run the Spring Boot application

Open the static frontend and perform signup/login actions

Notes

Ensure that the SECRET_HASH is correctly generated on the client side before initiating authentication.

User attributes such as email must be unique and verified based on Cognito configuration.

The example focuses on the ADMIN_NO_SRP_AUTH flow for simplicity.
