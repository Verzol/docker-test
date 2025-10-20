# Docker-running
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

