
#### example 
https://turreta.com/2020/02/29/using-an-sftp-server-apache-camel-and-spring-boot/
#### official document 
https://camel.apache.org/components/latest/sftp-component.html

#### Instructions
ssh-keygen -t rsa -f id_rsa

##### destination folder 
It will put the file in the home folder even you pointed a abosolute folder
e.g. you want put the test.txt file into /app/file, it will actually put the file in /home/user/app/file/test.txt
there are 2 solution for this
1) add ../,,/ for the destination folder
2) [apprunner@localhost apps]$ ln -s /apps/filein filein
     
##### generate the key with passphrase 
ssh-keygen -t rsa -f rsa_key
Enter passphrase (empty for no passphrase): changeit
##### verify the sftp key
sftp -o IdentityFile=C:/Users/lenovo/.ssh/rsa_key apprunner@192.168.126.120
Enter passphrase for key 'C:/Users/lenovo/.ssh/rsa_key':
Connected to apprunner@192.168.126.120.
sftp>
##### test the file transfer with passphrase 
put file in F:\apps\transfer\in and the file transferred successfully 

