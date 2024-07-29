# AWS Integration

This project is a Spring Boot application that integrates with various AWS services.



## Features

- Upload files to S3
- Download files from S3
- Configurable via `application.properties`
- Easily extendable to support additional AWS services

## Requirements

- Java 17
- Maven
- AWS Account with appropriate access

## Installation

1. Clone the repository:

```bash
git clone https://github.com/beskat/aws-integration.git
cd aws-integration
```
2. Build the project using Maven:

```bash
mvn clean install
```

## Configuration

Configure your AWS credentials and bucket information in the `application.properties` file:

```properties
aws.accessKey=your_access_key
aws.secretKey=your_secret_key
aws.region=your_aws_region
aws.s3.bucket=your_s3_bucket_name
```


## Usage

Run the Spring Boot application:

```bash
mvn spring-boot:run
```

The application will start and be accessible at `http://localhost:8080`.
