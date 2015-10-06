
var bds_lang = {
// Fomr Article Manager
    ArticleManager: {
        NO_DATA: "Không có dữ liệu thống kê theo ngày!",
        DATE: "Ngày",
        VIEW: "Lượt xem",
        NO_SELECT: "Bạn chưa chọn tin rao để xóa.",
        DEL_CONFIRM: "Nếu bạn tự động xóa tin rao này Bạn sẽ không được hoàn tiền đăng tin.Bạn có chắc chắn muốn xóa?"
    },
// Fomr UserProductInsert Manager
    UserProductInsert: {
//InsertErrorMessage: "Thêm mới không thành công",
// Default Option
        OptionDefaultCity: "Tỉnh/Thành phố",
        OptionDefaultDistrict: "Quận/Huyện",
        OptionDefaultWard: "Phường/Xã",
        OptionDefaultProject: "Dự án",
        OptionDefaultStreet: "Đường/Phố",
        OptionDefaultProductType: "Hình thức",
        OptionDefaultProductCate: "Loại",
// Error messages
        ErrMsgSpecifyCharacter: "Bạn không thể nhập vào ký tự &gt; hoặc &lt;",
        ErrMsgRequired: "Bạn cần nhập thông tin",
        ErrMsgMaxLength: "Số ký tự cần nhỏ hơn",
        ErrMsgMinLength: "Số ký tự cần lớn hơn",
        ErrMsgMaxWord: "Số từ lớn hơn",
        ErrMsgMinWord: "Số từ nhỏ hơn",
        ErrMsgEmailRequired: "Vui lòng nhập email",
        ErrMsgNumberRequired: "Bạn cần nhập số",
        ErrMsgSelectionRequired: "Bạn cần chọn thông tin",
        ErrMsgFormatEmailInValid: "Vui lòng nhập đúng định dạng email",
        ErrMsgAreaLessThanZero: "DT phải > 0",
        ErrMsgFacadeLessThanZero: "Mặt tiền phải > 0",
        ErrMsgEntryLessThanZero: "Đường vào phải > 0",
        ErrMsgFloorLessThanZero: "Số tầng phải > 0",
        ErrMsgFloorMoreThan255: "Số tầng phải <=255",
        ErrMsgRoomLessThanZero: "Số phòng ngủ phải > 0",
        ErrMsgRoomMoreThan255: "Số phòng ngủ phải <=255",
        ErrMsgToiletLessThanZero: "Số toilet phải > 0",
        ErrMsgToiletMoreThan255: "Số toilet phải <=255",
        ErrMsgSummary: "Có các thông tin bị lỗi!",
        ErrMsgTitle: "Vui lòng nhập tiêu đề tin đăng của bạn. Tối thiểu là 30 ký tự và tối đa là 99 ký tự",
        ErrMsgTitle1: "Vui lòng nhập tiêu đề tin đăng của bạn. Tối thiểu là ",
        ErrMsgTitle2: " ký tự và tối đa là ",
        ErrMsgTitle3: " ký tự.",
        ErrMsgPrice: "Bạn cần nhập giá",
        ErrMsgUnitPrice: "Đ/v giá không được là Thỏa Thuận",
// Alert messages
        AlertMsgDeleteDraft: "Tin nháp đã được xóa thành công.",
        AlertMsgFormatEmailInvalid: "Email không đúng, mời bạn vui lòng nhập email.",
    },
    EmailRegister: {
        SELECT_INFO: "Bạn cần chọn thông tin",
        ENTER_TITILE: "Bạn hãy nhập tiêu đề thư báo!",
        ENTER_EMAIL: "Bạn hãy nhập lại email cho đúng!",
        ENTER_AGAIN: "Bạn chưa nhập đúng thông tin, hãy nhập lại cho đúng!"
    },
    SearchForm:{
        TRANS: "Chọn hình thức",
        TYPE: "Chọn loại nhà đất",
        CITY: "Chọn Tỉnh/Thành phố",
        DIST: "Chọn Quận/Huyện",
        WARD: "Chọn Phường/Xã",
        STREET: "Chọn Đường/Phố",
        PROJECT: "Chọn Dự án"
    },
    UploadImageControl: {
        DropText1 : "Tin rao có ảnh sẽ được xem nhiều hơn gấp 10 lần và được nhiều người gọi gấp 5 lần so với tin rao không có ảnh. Hãy đăng ảnh để được giao dịch nhanh chóng!",
        DropText2: "Tin rao có trên 5 ảnh sẽ được liên hệ nhiều hơn so với các tin khác. Hãy đăng nhiều ảnh để giao dịch nhanh chóng!",
        UploadButtonName: "upload-button",
        DragMessage: "Hoặc kéo và thả ảnh từ máy tính vào đây",
        Completed: "Hoàn thành",
        Uploading: "Đang tải lên",
        DropProcessing: "Đăng ảnh thật nhanh bằng cách kéo và thả ảnh vào khung. Thay đổi vị trí của ảnh bằng cách kéo ảnh vào vị trí mà bạn muốn!",
        SelectedFile : "Chọn file",
        DeleteMessage: "Ảnh sau khi xóa không thể khôi phục được. Bạn có chắc chắn muốn xóa ảnh này?",
        ConfirmDelete: "Bạn có chắc chắn muốn xóa ảnh này?",
        AvatarImage: "Ảnh đại diện",
        TypeError: "không đúng định dạng ảnh. Chỉ chấp nhận những định dạng",
        SizeError: "có kích thước quá lớn, Kích thước cho phép",
        MinSizeError: "có kích thước quá nhỏ, Kích thước giới hạn",
        TooManyItemsError1: "Bạn đã upload quá nhiều ảnh cho phép",
        TooManyItemsError2: "Số lượng tối đa cho phép",
        WaitingForResponse: "Đang xử lý...",
        ConfirmMessage: "Bạn có chắc chắn muốn xóa",
        DeletingStatusText: "Đang xóa...",
        DeletingFailedText: "Xóa thất bại",
        ChooseFile: "Chọn file"
    },
    GoogleMaps:
    {
        Address: "Địa chỉ",
        AddressIncorrect: "Địa chỉ không chính xác",
        TpHCM: "Hồ Chí Minh"
    },
    UserChangeInfo:
    {
        VietNam: "Việt Nam",
        ErrMsgRequired: "Bạn cần nhập thông tin",
        ErrMsgSelectionRequired: "Bạn cần chọn thông tin",
        ErrMsgFormatEmailInValid: "Vui lòng nhập đúng định dạng email",
        ErrMsgNumberRequired: "Bạn cần nhập số",
        OptionDefaultBrokeInfo: "Lĩnh vực",
        DeleteBrokeTitle: "Xóa",
        DeleteBrokeAlert: "Bạn chắc chắn muốn xóa ?"
    },
    Commons: {
        Confirm: "Bạn có chắc chắn muốn xóa?"
    }
};
