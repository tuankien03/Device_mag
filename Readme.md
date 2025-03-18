0. Chung
Dùng chung 1 ngôn ngữ.
- search theo name thì không phân biệt hoa vs thường.
THêm message error (mat-error) trong các form.
 Giá trị string/number dùng lại nhiều để thành hằng số.
Trong giao diện các bảng nếu độ dài dữ liệu không quá dài thì không hiện thanh scroll
Đồng bộ button trên các giao diện: nếu dùng nút text thì chung nút text, nếu icon chung thì dùng chung icon.
ADMIN:
1. Thêm User
Khi không điền password thì message validate phải là require, hiện tại đang là check lenght password.
- Validate username, password trên frontend trước khi call api.
Giá trị search toàn dấu cách thì không cho search.
- Form chỉnh sửa user thì không cho sửa password, nếu muốn sửa thì tách riêng ra một màn hình riêng - tính năng reset password riêng.
2. Thiết bị có sẵn
- Form gán thiết bị thì danh sách user get ra chỉ lấy USER, hiện đang lấy cả ADMIN.
3. Thiết bị đang cho mượn
Lỗi không hiển thị được danh sách thiết bị đang cho mượn.

USER:
- Tách riêng lịch sử mượn thiết bị với bảng thiết bị đang mượn, hiện tại đang chung bảng với nhau.
- Đổi mật khẩu thì mật khẩu cũ không được trùng với mật khẩu mới.
THêm trường confirm mật khẩu mới khi change password.
Logout khi đổi mật khẩu.