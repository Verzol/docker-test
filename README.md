# Docker-running & Triển khai Ứng dụng Web + MySQL
Tạo Image cho ứng dụng web SpringBoot

Lưu dữ liệu vào MySQL (container) có mount dữ liệu (bind mount or volume mount)

## Cấu trúc thư mục

<img width="356" height="114" alt="image" src="https://github.com/user-attachments/assets/ab6ab8d5-7982-4ccc-a23e-805abf4d515f" />

## Khởi động
Di chuyển tới folder "docker", và chạy "docker compose up -d --build".

<img width="1457" height="874" alt="image" src="https://github.com/user-attachments/assets/1034c995-bc00-4044-b074-65666e3e5cea" />
Kết quả sau khi chạy thành công.

"docker ps"

<img width="1474" height="138" alt="image" src="https://github.com/user-attachments/assets/0000b076-c28e-44d6-a225-c468c12de5a0" />

## Kiểm tra ứng dụng 
Chạy lệnh "curl http://localhost:8080/actuator/health". Kết quả mong đợi là "{"status":"UP"}"

## Test API
Tạo user mới
curl -X POST -H "Content-Type: application/json" \
-d "{\"name\":\"Dem\"}" http://localhost:8080/api/users

Xem danh sách user
curl http://localhost:8080/api/users

<img width="1247" height="462" alt="image" src="https://github.com/user-attachments/assets/ca68a13c-71b1-496d-8c3d-be316b685308" />

## Kiểm tra dữ liệu trong MySQL Container
Lệnh "docker exec -it mysql8 mysql -uappuser -papppass"

Trong MySQL:
"USE appdb;

SELECT * FROM user;"

<img width="269" height="170" alt="image" src="https://github.com/user-attachments/assets/f92e3882-46f5-497e-a4e1-d047d701822f" />

## Kiểm tra mount dữ liệu
Dừng cointainer bằng lệnh "docker compose down"

Thư mục "db_data":

<img width="1758" height="907" alt="image" src="https://github.com/user-attachments/assets/9ee7aa54-eae6-442c-aa8d-b1c73a65e4e1" />

Kết quả sau khi tắt/mở lại docker:

<img width="1481" height="708" alt="image" src="https://github.com/user-attachments/assets/76238b88-bb5c-49dc-895d-0edf243d4e91" />

## Triển khai ứng dụng Web và MySQL

Sử dụng Dockerfile để build "docker build -t webapp-k8s:latest ."

<img width="1400" height="579" alt="image" src="https://github.com/user-attachments/assets/dd900f0e-e324-42c2-af60-76d66eb0b7ad" />

Kiểm tra xem đã build thành công chưa:

<img width="646" height="155" alt="image" src="https://github.com/user-attachments/assets/30379896-e1b0-4ef0-be56-8c4c9a4b8ea9" />

Tạo folder "k8s-docker-test" gồm 4 file ".yaml" nội dung như trong repo, và apply 4 file trên "kubectl apply -f ."

Chạy "kubectl get pods -l app=webapp" để theo dõi xem app đã chạy chưa:

<img width="680" height="67" alt="image" src="https://github.com/user-attachments/assets/98533df8-f4d3-402b-8c76-e9d537d64f27" />

Kiểm tra cho Status là "Running". Để kiểm tra, truy cập vào "http://localhost"

<img width="1919" height="975" alt="image" src="https://github.com/user-attachments/assets/975f319b-9446-4995-8348-29a64aff74ad" />

Thêm thử 2 tên User vào

<img width="1919" height="975" alt="image" src="https://github.com/user-attachments/assets/0ff17b9c-255b-44e8-8ff9-0ac0a88592f1" />

### Test tính toàn vẹn của data 

Xóa pod MySQL:

<img width="860" height="228" alt="image" src="https://github.com/user-attachments/assets/fc7b4cc8-18db-4246-8626-4fccba2ed131" />

Sau đó chạy lại "kubectl get pods", xuất hiện pod mysql mới và dữ liệu trên "http://localhost" vẫn còn nguyên.

<img width="1919" height="976" alt="image" src="https://github.com/user-attachments/assets/5524e94a-66a6-460d-9fa7-5a9d802a9262" />

### Test với terminal và MySQL Workbench

<img width="835" height="215" alt="image" src="https://github.com/user-attachments/assets/94d8b16e-46e5-41ab-bb88-6068ec6699c4" />

Tạo 1 connection mới trong MySQL Workbench như hình:

<img width="878" height="590" alt="image" src="https://github.com/user-attachments/assets/2b93516f-4276-4a47-b4a5-5091a985c95e" />

<img width="1919" height="1079" alt="image" src="https://github.com/user-attachments/assets/04d96619-6172-4813-9fe1-4516252c1107" />

Như hình trên => dữ liệu được lưu => thành công triển khai MySQL trên K8S.

