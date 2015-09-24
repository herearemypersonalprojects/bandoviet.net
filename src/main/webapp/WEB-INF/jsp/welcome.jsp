<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en-us">
<head>
<title>Mappist</title>
<link rel="icon" type="image/png" href="img/map.png"> <!-- http://www.favicon.cc -->
<style>
.country {
    float: left;
    margin: 5px;
    padding: 15px;
    width: 300px;
    height: 300px;
    border: 1px solid black;
    cursor:pointer;
} 
</style>

<script>
$(document).ready(function() {
	$('#vietnam').click(function() {
		window.location.href = "/vietnam";
	});
});
</script>
</head>
<body>

<h1>Welcome to the world map of diaspora</h1>

<div id="japan" class="country">
  <h2><img border="1" style="height: 20px;" alt="Logo" src="img/flags/japan.png"> 海外で日本の</h2>
  <p>日系人（にっけいじん）とは、日本以外の国に移住し当該国の国籍または永住権を取得した日本人、およびその子孫のこと。現在約300万人以上存在すると推定されている[1]。いわゆるハーフなどの混血の人物も含む。日系人のうち日本に居住する者を「在日日系人」、日本以外に居住する者を「海外日系人」と呼びわけることもある[要出典]。在日日系人は約30万人存在する。</p>
</div>

<div id="france" class="country">
  <h2><img style="height: 20px;" alt="Logo" src="img/flags/france.png"> Les Français à l'étranger</h2>
  <p>Le nombre de Français inscrits sur les registres des consulats français était de 1 642 953 (2013). Ces données ne représentent cependant qu'une partie des ressortissants français vivant en dehors du territoire national.</p> 
  <p>Dans la mesure où l'inscription au registre mondial n’est pas obligatoire, un certain nombre de ressortissants français ne se font pas connaître des services consulaires. Bien qu’incomplètes, certaines projections permettent d’estimer que 2,5 millions de Français sont établis hors de France</p>
</div>

<div id="vietnam" onClick="window.location.href = '/vietnam';" class="country">
  <h2><img style="height: 20px;" alt="Logo" src="img/flags/vietnam.png"> Người Việt ở nước ngoài</h2>
  <p>Theo số liệu của Học Viện Ngoại Giao năm 2012, cộng đồng người Việt Nam ở nước ngoài có trên 4 triệu người và phân bố không đồng đều tại 103 nước và vùng lãnh thổ trên khắp thế giới, 98% trong số đó tập trung ở 21 nước tại Bắc Mỹ, Châu Âu, Đông Nam Á, Đông Bắc Á và châu Đại Dương.</p>
  <p>Việt kiều ở nước ngoài là một nguồn vốn về kinh tế và nhân lực cho Việt Nam và có sức tiêu thụ cao. Kiều hối cũng là một nguồn doanh thu quan trọng cho Việt Nam.</p>
</div>

<div class="country">
  <h2><img border="1" style="height: 20px;" alt="Logo" src="img/flags/poland.png"> polska za granicą</h2>
  <p>Liczebność diaspory polskiej ocenia się obecnie na 15 milionów. Największą grupę stanowi oczywiście Polonia amerykańska. W Europie najliczniejsze skupiska emigrantów polskich znajdują się w Niemczech i Francji, a także w Wielkiej Brytanii.</p>
  <p>Polscy emigranci nie zapominają o swoich korzeniach. W wielu krajach powstały ośrodki kultury polskiej, w których potomkowie polskiej emigracji mogą się uczyć języka polskiego i zgłębiać polską kulturę. </p>
</div>

<div class="country">
  <h2><img style="height: 20px;" alt="Logo" src="img/flags/germany.png"> Deutsch im Ausland</h2>
  <p>Die deutsche Volksgruppe in 81 Ländern gemeldet.</p>
</div>

<div class="country">
  <h2><img style="height: 20px;" alt="Logo" src="img/flags/china.png"> 中国海外</h2>
  <p>目前的海外華人主要生活於當地為相對多數民族的新加坡及在當地為相對少數民族的馬來西亞、泰國、菲律賓、印度尼西亞與越南。這些地區的海外華人主要来自16至19世紀福建省與廣東省。而隨著中國人的收入、教育程度資加，技術移民人數也開始增加，除了歐美、人口稀少的澳洲、紐西蘭也成了今天的熱門移民地，文化相對近且經濟發展的東南亞也有部分移民。而前往遙遠非洲、南美洲（如巴西）的也在有所增多。</p>
</div>

<div class="country">
  <h2><img style="height: 20px;" alt="Logo" src="img/flags/italy.png"> Italiani all'estero</h2>
  <p>Attualmente esistono circa 80 milioni di oriundi italiani in differenti nazioni del mondo: i più numerosi sono in Brasile, Argentina, e Stati Uniti d'America.</p>
  <p>In molti Paesi, specialmente del Sud America, le stime sono molto approssimative poiché non esiste alcun tipo di censimento sulle proprie origini (come accade invece in Stati Uniti o Canada).</p>
  <p>Comunque, la cifra totale degli oriundi italiani oscilla approssimativamente intorno agli 80 milioni, secondo i Padri Scalabriniani.</p>
</div>

<div class="country">
  <h2><img style="height: 20px;" alt="Logo" src="img/flags/laos.png"> ລາວໃນຕ່າງປະເທດ</h2>
  <p>ການພັດຖິ່ນລາວປະກອບດ້ວຍປະມານຫຼາຍກວ່າເຄິ່ງຫນຶ່ງເປັນລ້ານຄົນລາວ, ລູກຫລານທັງສອງຂອງພະຍົບຕົ້ນຈາກປະເທດລາວ, ເຊັ່ນດຽວກັນກັບຊາວອົບພະຍົບທີ່ຜ່ານມາຫຼາຍຜູ້ທີ່ໄດ້ escaped ປະເທດດັ່ງຕໍ່ໄປນີ້ takeover ການຂອງຕົນເປັນຜົນຂອງສົງຄາມກາງເມືອງລາວໄດ້. ຄົນສ່ວນໃຫຍ່ໃນຕ່າງປະເທດລາວດໍາລົງຊີວິດຢູ່ໃນພຽງແຕ່ສາມປະເທດ: ປະເທດໄທ, ປະເທດສະຫະລັດອາເມລິກາ, ແລະປະເທດຝຣັ່ງ.</p>
</div>

<div class="country">
  <h2><img style="height: 20px;" alt="Logo" src="img/flags/malaysia.png"> Malaysia di luar negara</h2>
  <p>The World Bank estimated that at least 800,000 and up to 1.4 million Malaysians were currently living overseas. Among the diaspora, more than a third of them are over 25, which the World Bank has identified as the brain drain, or rather Malaysian citizens that are living abroad and contributing to countries other than their own. As of 2000, 46% of the Malaysian diaspora reside in Singapore and actually make up 45% of Singapore’s resident immigrant population.</p>
</div>
</body>
</html>