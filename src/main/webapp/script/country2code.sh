#!/bin/sh
#
# Country to code (eg, France to FR)
#

MYSQL_OPTS="mysql -u map -pmap map"

mysql ${MYSQL_OPTS} <<EOF 
	update table place set country = 'FR' where country = 'Pháp';
	update table place set country = 'FR' where country = 'France';
	update place set country = 'US' where country = 'Hoa Kỳ';
	update place set country = 'CA' where country = 'Canada';
	update place set country = 'DE' where country = 'Đức';
	update place set country = 'BE' where country = 'Bỉ';
	update place set country = 'IT' where country = 'Ý';
	update place set country = 'ES' where country = 'Tây Ban Nha';
	update place set country = 'NL' where country = 'Hà Lan';
	update place set country = 'IT' where country = 'Đan Mạch';
	update place set country = 'IT' where country = 'Thổ Nhĩ Kỳ';
	update place set country = 'IT' where country = 'Nhật Bản';
	update place set country = 'IT' where country = 'Trung Quốc';
	update place set country = 'IT' where country = 'Hàn Quốc';
	update place set country = 'IT' where country = 'South Korea';
	update place set country = 'IT' where country = 'Hong Kong';
	update place set country = 'IT' where country = 'United States';
	update place set country = 'IT' where country = 'Senegal';
	update place set country = 'IT' where country = 'Russia';
	update place set country = 'IT' where country = 'Morocco';
	update place set country = 'IT' where country = 'Algeria';
	update place set country = 'IT' where country = 'Mexico';
	update place set country = 'IT' where country = 'Australia';
	update place set country = 'IT' where country = 'Taiwan';
	update place set country = 'IT' where country = 'Netherlands';
	update place set country = 'IT' where country = 'Czech Republic';
	update place set country = 'IT' where country = 'Austria';
	update place set country = 'IT' where country = 'Hungary';
	update place set country = 'IT' where country = 'Belgium';
	update place set country = 'IT' where country = 'Finland';
	update place set country = 'IT' where country = 'Germany';
	update place set country = 'IT' where country = 'Cambodia';
	update place set country = 'AO' where country = 'Angola';
	update place set country = 'NG' where country = 'Nigeria';
	update place set country = 'VE' where country = 'Venezuela';
	update place set country = 'IL' where country = 'Israel';
	update place set country = 'EG' where country = 'Egypt';
	update place set country = 'JP' where country = 'Japan';
	update place set country = 'GR' where country = 'Greece';
EOF
