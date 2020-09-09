# API Gateway

HTTPS configuration, two ways:

1. NGINX configuration SSL certificate

2. API Gateway supports HTTPS

Currently, the API gateway supports HTTPS and has been adapted. The protocol when forwarding requests will be converted to HTTP

See the ssl part of `gateway-service.yml` for configuration

# NGINX configure HTTPS

Edit the conf/nginx.conf file in the root directory of Nginx. The revised content is as follows:

```
server {
     listen 443; #SSL access port number is 443
     server_name www.domain.com; #Fill in the domain name of the binding certificate
     ssl on; #Enable SSL function
     ssl_certificate 1_www.domain.com_bundle.crt; #Certificate file name
     ssl_certificate_key 2_www.domain.com.key; #Private key file name
     ssl_session_timeout 5m;
     ssl_protocols TLSv1 TLSv1.1 TLSv1.2; #Please configure according to this protocol
     ssl_ciphers ECDHE-RSA-AES128-GCM-SHA256:HIGH:!aNULL:!MD5:!RC4:!DHE; #Please follow this package configuration, configure the encryption suite, and write it in accordance with the openssl standard.
     ssl_prefer_server_ciphers on;
     location / {
         root /var/www/www.domain.com; #Website homepage path. This path is for reference only, please follow the actual directory for details.
         index  index.html index.htm;
     }
 }
 ```
 
 # Follow-up plan
 
 1. Storage of filters and predicates
 2. Service multi-instance deployment, support traffic switching, gray release, ABTest, idea: based on service label (main, standby), weighting method