# product_comparison
A product comparison service that lets you decide from which seller (website/retail shop) should you buy your product.

## Approch

The service is written in ```Java``` and uses ```Spring Boot``` framwork to create a product comparison micro service.

## How to create docker image and Run
Skip Tests:
```
mvn clean package -Dmaven.test.skip=true
```
or 
```
mvn clean package
```

Now in project directory run following command:
```
docker build -t product-comparison .
```

This is used for building image with tag `product-comparison` and `.` signifies the
`Dockerfile` is present in current directory

Now to create container from this image we need to run following command:
```
docker run -p 8080:8080 -it product-comparison:latest
```

the following will expose the docker 8080 to outside 8080 port.
