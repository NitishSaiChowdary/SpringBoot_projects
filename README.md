AWS Notes
https://182582698902.signin.aws.amazon.com/console
Nsai
Nitish@2003

-->What is IAM? -
IAM stands for Identity and Access Management in AWS
A service that controls access to AWS resources by managing users, groups, roles, and permissions. It is a core security service that handles authentication (verifying who is accessing the resources) and authorization(determining what actions they are allowed to perform).  

-->what is IAM Users?
Users: A person or application that can access AWS resources.
-->what is IAM Groups?
Collections of users, to which you can assign permissions.
-->what is IAM Role?
An IAM Role is a set of permissions that anyone can assume temporarily to do a specific task.
-->what is IAM Policies?
JSON documents that define permissions. You attach policies to users, groups, or roles to grant or deny access to resources and services
**********************************
S3 0n AWS-Simple Storage Service -
**********************************
-->what is S3?
Amazon s3 is a highly scalable, durable, and secure object storage service. it allows you to store any amount of data such files , images, videos, logs, backups, etc.....
*************
key Concepts-
*************
S3 bucket- Like a folder . top-level container for your files(objects)
Object- Any file you upload (with metadata and key)
key- Unique name for the object in a bucket .
Region- Aws region where your bucket resides.
Storage Classes : Different cost/durability options like standard , intelligent-Tiering , etc 
Public Access- Controls Who Can access the bucket or files.


What is ec2 ?
Amazon Elastics Compute Cloud is a web service that lets you launch and manage virtual servers called instances in the AWS cloud. it delivers secure, on-demand and scalable compute capacity , so you can add or remove servers within minutes and pay only for the resources you actually use.


Elastic-scable
compute-processing power 
cloud AWS infa
Step 2: Remove inherited permissions
icacls springboot-emp-app11.pem /inheritance:r

Step 3: Give read permission only to your user
icacls springboot-emp-app11.pem /grant:r nitish.yalamarthi:R
✔ This is the Windows equivalent of chmod 400.
✅ Verify permissions
icacls springboot-emp-app11.pem

 1. Upload JAR to EC2
scp -i "springboot-emp-app11.pem" SpringBoot_emp1-0.0.1-SNAPSHOT.jar ec2-user@ec2-54-85-144-187.compute-1.amazonaws.com:/home/ec2-user/

ssh -i "springboot-emp-app11.pem" ec2-user@ec2-54-85-144-187.compute-1.amazonaws.com
 2. SSH into EC2 Instance
ssh -i <your-key.pem> ec2-user@<public-ip>


Windows (Using PowerShell or CMD):
Open PowerShell
Navigate to the folder containing your .pem file
Run:
ssh -i .\your-key.pem ec2-user@<public-ip>

📦 3. Install Java (Amazon Linux 2, Java 17)
sudo yum update -y
sudo amazon-linux-extras enable corretto17
sudo yum install java-17-amazon-corretto -y
java -version

4. Run Spring Boot JAR with nohup
After keeping jar file in the ec2 to run the project-
--> nohup java -jar SpringBoot_emp1-0.0.1-SNAPSHOT.jar > output.log 2>&1 &

5. View Logs in Real-Time
tail -f output.log


AWS RDS  (Relation databases)

RDs (Relation databases service) is managed database service from Aws that make it easy to set up , operate , and scale a relational database in the cloud 


What is DynamoDB?
a fully managed NoSQL key-value and document database by AWS

Keypoint --

NO server provisioning or management 
Scales Automatically 
low latency performance
built in security (IAM, encryption)
Offers DAX (caching) , Streams, TTL , and Global Tables 

What is AWS Lambda?

AWS Lambda is a serverless compute service that lets you run code without creating or managing servers.
Why is it called Serverless?

It does NOT mean there are no servers.
It means you don’t manage them.
AWS handles:
Servers
OS
Scaling
Patching
Availability
You focus only on code + logic.

Serverless with AWS Lambda --
You don't manage servers 
you only upload your code
Aws automatically scales and runs it on demand
you only pay for execution time , not idle time 
 
AWS CLI 

What is Aws CLI? (Command Line Interface)
The Aws CLI is a unified tool to manage your AWS services . with just one tool to download and configure you can control multiple Aws services from the command line and automate them through scripts.
 

AWS SQS-
what is SQS?
Amazon Simple queue service is a fully managed message queueing service 
aws sqs send-message -- queue-url https://sqs.ap-south-1.amazonaws.com/137130623544/myfirstqueue -- message-body "Order #123 placed"

aws sqs receive-message -- queue-url https://sqs.ap-south-1.amazonaws.com/137130623544/myfirstqueue
 
aws sqs delete-message -- queue-url <QUEUE_URL>-- receipt-handle <HANDLE>


AWS SNS?
What is SNS (Simple Notification Service)

what is AWS secrets Manager?
AWS Secrets Manager is a fully managed service that helps you securely store, manage, and retrieve
secrets like database credentials, API keys, OAuth tokens, and other sensitive configuration data.
It automatically handles encryption, rotation, fine-grained access control (via IAM), and integrates
easily with AWS services and applications. Instead of hardcoding credentials in your code, you fetch
them securely at runtime from Secrets Manager.

Docker---
what is docker?
Docker is a tool that allows you to package your application and its entire environment into one unit.
  

The Docker Architecture --

users--> Docker CLI / Docker Desktop --> Docker daemon  --> image,
containers,  networker , volumes
--> docker registry , docker hub 

install Docker--
https://www.docker.com/products/docker-desktop/
install windows amd-64 

then check the cmd - docker --version 
after  check docker ps
it will show--error during connect: this error may indicate that the docker daemon is not running: Get "http://%2F%2F.%2Fpipe%2Fdocker_engine/v1.45/containers/json": open //./pipe/docker_engine: The system cannot find the file specified.

then open docker desktop now check it will open 

docker commands---

->docker ps -a 
it show all cont 

 docker run         Create and run a new container from an image
 docker exec        Execute a command in a running container
 docker ps          List containers
 docker build       Build an image from a Dockerfile
 docker bake        Build from a file
 docker pull        Download an image from a registry
 docker push        Upload an image to a registry
 docker images      List images
 docker login       Authenticate to a registry
 docker logout      Log out from a registry
 docker search      Search Docker Hub for images
 docker version     Show the Docker version information
 docker info        Display system-wide information
 docker rm          Remove one or more containers
 docker rmi         Remove one or more images
 docker run -it     it run coutn


1st step 
docker pull 
2nd step 
docker images
3rd  step 
docker create <image name>
4th step
docker ps  -a
5th step 
docker start <cont ID>
6th step 
docker ps  -a
7th step 
docker stop <con ID>


to copy the jar.file to container 
-->open the terminal in sts then go to --> java -jar target/file.jar
-->now check the OpenJDK file          --> docker exec containername ls -a 
--> check the tmp file                 --> docker exec containername ls /tmp
-->now copying jar.file to container   --> docker cp target/file.jar container name:/tmp
--.commit the                          -->docker commit --change "CMD [\"java\",\"-jar\",\"/tmp/SpringBoot_emp1-0.0.1-SNAPSHOT.jar\"]" flamboyant_euclid nitish/springboot:v2
-->now the image                       -->docker run -d -p 8089:8089 nitish/springboot:v2

after these steps the project will run 



now create the doker file 
steps
FROM openjdk:27-ea-slim-trixie
ADD  target/SpringBoot_emp1-0.0.1-SNAPSHOT.jar SpringBoot_emp1-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","SpringBoot_emp1-0.0.1-SNAPSHOT.jar"]

next-
docker build -t nitish/spring:v3 . 

docker run -p 8089:8089 nitish/spring:v3


docker volumes:
docker volume create	Create a volume
docker volume inspect	Display detailed information on one or more volumes
docker volume ls	List volumes
docker volume prune	Remove unused local volumes
docker volume rm	Remove one or more volumes
docker volume update	Update a volume (cluster volumes only)






CI/CD - 
STEP - CREATE THE SPRING BOOT APP ---
2- AFTER CREATE THE DOCKER FILE ---
FROM openjdk:27-ea-slim-trixie
WORKDIR /APP
ADD  target/SpringBoot_emp1-0.0.1-SNAPSHOT.jar SpringBoot_emp1-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","SpringBoot_emp1-0.0.1-SNAPSHOT.jar"]

3-CREATE THE ECR ELASTIC CONTAINER REGISTRY

4-CREATE BUILDSPE.YAML  

5. CREATE THE AWS CODE BUILD 

6. CREATE THE ELASTIC CONTAINER SERVICE 
   -> CREATE THE NEW TASK DEFINITION 
   -> CREATE THE CLUSTER 



CI/CD 
Azure with Git 
STEP 1: Project Structure (Important)
Make sure your Maven project looks like:
project-root/
 ├── src/
 ├── pom.xml
 └── azure-pipelines.yml  --> (we will create this)
