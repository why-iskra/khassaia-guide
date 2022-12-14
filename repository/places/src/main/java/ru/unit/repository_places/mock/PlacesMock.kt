package ru.unit.repository_places.mock

import ru.unit.repository_places.models.PlaceModel
import javax.inject.Inject

class PlacesMock @Inject constructor() {

    val list = listOf(
        PlaceModel(
            id = 0,
            lat = 53.190683,
            lon = 90.095795,
            type = PlaceModel.Type.PLACE,
            name = "Хакасский национальный музей-заповедник «Казановка»",
            description = "Один из самых удивительных и без сомнения самый крупный музей под открытым небом в Республике Хакасия расположен в Аскизском районе, в 5-и километрах от деревни Казановка, в предгорьях Кузнецкого Алатау. Площадь музея-заповедника составляет более 18 тыс. га и содержит огромное количество уникальных образцов историко-культурного наследия хакасского народа. Контактный телефон: +7 913 057 55 81. Эл. почта: muskaz96@r-19.ru.",
            imgUrl = "https://culture19.ru/images/content/news/48/l-4794-muzey-zapovednik-kazanovka-prezentuet-novuyu-muzeynuyu-programmu.jpg"
        ),
        PlaceModel(
            id = 1,
            lat = 53.129536,
            lon = 90.538812,
            type = PlaceModel.Type.PLACE,
            name = "Аскизский краеведческий музей им. Н.Ф. Катанова",
            description = "Основным направлением деятельности музея является краеведение. В музее функционируют следующие экспозиции: «Этнография хакасского народа», «Ученый Николай фёдорович Катанов»,  «Аскизский район», «Этих дней не смолкнет слава...», «Пётр Иванович Кузнецов в селе Аскиз». Аскизский районный краеведческий музей был открыт на базе школьного музея 28 сентября 1987 г. На момент своего открытия музей насчитывал 200 экспонатов. Основными направлениями в деятельности нового музея стали археология, этнография и тема Гражданской войны в Хакасии. Уже в 1990-е годы количество музейных предметов приблизилось к 2000 ед. хранения, среди которых бесценными были этнографические материалы. 3 сентября 2003 г. музей переехал в центр села Аскиз по адресу: ул. Советская, 19. Это двухэтажное деревянное здание построено в середине XIX века золотопромышленником и меценатом, городским головой г. Красноярска Петром Ивановичем Кузнецовым. Контактный телефон: 8 (39045) 9-10-58. Эл.почта: askizmuzei@mail.ru.",
            imgUrl = "https://cdn.culture.ru/images/59090d18-4fa1-55ed-a2da-67cb64571955"
        ),
        PlaceModel(
            id = 2,
            lat = 53.720434,
            lon = 91.473981,
            type = PlaceModel.Type.PLACE,
            name = "Хакасский национальный краеведческий музей им. Л.Р. Кызласова",
            description = "Ведущий музей Хакасии. Здесь собраны памятники от неолита до позднего средневековья. Главные сокровища коллекции - знаменитые древнеенисейские каменные изваяния окуневской археологической культуры (кон. III - нач. 11 тыс. до н. э.) из песчаника и гранита. Контактный номер: +7 (3902) 30-64-11. Эл.почта: hnkm@r-19.ru.",
            imgUrl = "https://dosug-abakan.ru/upload/iblock/6f2/6f242bb296a25b1b784c4761233e1446.jpg"
        ),
        PlaceModel(
            id = 3,
            lat = 53.837079,
            lon = 91.400681,
            type = PlaceModel.Type.PLACE,
            name = "Музей «Древние Курганы Салбыкской степи»",
            description = "Основными объектами музея являются Большой Салбыкский курган и курган Барсучий Лог и менгир ворота в Салбыкскую долину Большой Салбыкский курган - самый грандиозный мегалитический обьект из известных подобных памятников бассейна Среднего Енисея, место погребения могущественного вождя кочевых племен и выдающийся сакральный, архитектурный и астрономический памятник. Контактный телефон: 8 (39032) 2-02-55. Эл. почта: salbyk@mail.ru.",
            imgUrl = "https://culture19.ru/uploads/files/docs/deyatelnost/protivodeystvie-korruptsii/svedeniya-o-dohodah/Salbyk_2.png"
        ),
        PlaceModel(
            id = 4,
            lat = 54.7005,
            lon = 89.7634,
            type = PlaceModel.Type.PLACE,
            name = "Музей-заповедник Сундуки",
            description = "С этим местом в Хакасии связано самое большое количество слухов и легенд, ведь Сундуки существуют уже 40 тыс. лет. Представьте себе долину диаметром 12-15 км, окруженную горами. Практически в центре этой долины стоят каменные пирамиды — в народе их называют «Сундуки» за необычайно правильную форму вершин в виде квадратов. Длина этих пирамид чуть более 300 м, высота -70 м. Сундуки отдаленно напоминают пирамиды ацтеков и майя. У каждой горы есть свой порядковый номер, самый северный — Первый сундук, самый южный — Пятый. С их вершин видна бескрайняя степь с курганами и линиями древних оросительных каналов. На скальных отвесах Четвертого сундука расположено несколько групп петроглифов (рисунков) — своего рода героический эпос в картинках, повествующий о подвигах предков хакасов на земле, в загробном мире и мире светлых богов. Наскальные рисунки были выбиты более 2000 лет назад. Контактный телефон: +7 (923) 395-54-86. ",
            imgUrl = "https://culture19.ru/uploads/files/docs/deyatelnost/protivodeystvie-korruptsii/svedeniya-o-dohodah/Sunduki.jpg"
        ),
        PlaceModel(
            id = 5,
            lat = 54.47003800,
            lon = 89.54956100,
            type = PlaceModel.Type.PLACE,
            name = "Крепость Тарпиг",
            description = "Крепость Тарпиг находится в 4 километрах к юго-востоку от улуса Колбякова на правом берегу реки Белый Июс. Тарпиг — руины боевого укрепления IX–XII веков в Минусинской котловине Республики Хакасия. Археологи обнаружили здесь три ряда оборонительных ограждений, а на территории крепости — предметы быта и оружие приенисейский воинов. Название крепости происходит от названия горы Тарпиг. Гору Тарпиг можно назвать музеем под открытым небом. Крепость представляет собой массивное сооружение из трёх линий стен, чей общий размер составляет 90 на 85 метров.",
            imgUrl = "https://cdn.culture.ru/c/805305.jpg"
        ),
        PlaceModel(
            id = 6,
            lat = 54.971174,
            lon = 89.589337,
            type = PlaceModel.Type.PLACE,
            name = "Сулекская писаница",
            description = "Сулекская писаница – один из ярчайших образцов наскальных изображений, представляющий собой скалу высотой около 600 метров, сплошь покрытую тысячами рисунков, тянущихся на 200 метров и относящихся к различным эпохам. Писаница получила свое название от фамилии рода Сулековых, чьи родовые места окружали ее. В народе её называют «Пичиктиг-Таг» (‘Писаная Гора’). Первые рисунки на писанице были нанесены 3-4 тысячи лет назад, последние – в XVIII веке, и у них совершенно разные техники исполнения. Изображения показывают сцены охоты, различные рыцарские сражения, лодки с гребцами, людей, обрабатывающих землю мотыгами, борьбу животных, сюжеты, связанные с шаманскими верованиями. Рисунки сопровождают тексты древнехакасского письма. Верхняя надпись «вечная скала» говорит о том, что эти рисунки оставлены на вечные времена. Контактный телефон: 8 (923) 391-37-77. Эл.почта: ya.kirova12@yandex.ru",
            imgUrl = "https://russianasha.ru/files/lib/images/51/07/sulekskaya-pisanitsa-1.jpg"
        ),
        PlaceModel(
            id = 7,
            lat = 54.8577509,
            lon = 89.267243,
            type = PlaceModel.Type.PLACE,
            name = "Саралинское изваяние Тас-Хыс (Каменная Дева)",
            description = "Саралинское изваяние Тас-Хыс (Каменная дева) - находится на восточной окраине села Сарала. В переводе с хакасского языка Тас-Хыс обозначает «Каменная Дева». Каменная скульптура обработана красным песчаником и достигает в высоту 2-х м. Верхушка изваяния, представляющая головной убор, отсутствует. Основа фигуры высечена и отшлифована в форме, отдаленно напоминающей женскую фигуру, с довольно четко выделяющимися женской грудью и беременным животом, на котором находится изображение дитя.",
            imgUrl = "http://posibiri.ru/wp-content/uploads/2015/05/%D0%A1%D0%B0%D1%80%D0%B0%D0%BB%D0%B8%D0%BD%D1%81%D0%BA%D0%BE%D0%B5-%D0%B8%D0%B7%D0%B2%D0%B0%D1%8F%D0%BD%D0%B8%D0%B5.jpg"
        ),
        PlaceModel(
            id = 8,
            lat = 54.615456,
            lon = 88.652238,
            type = PlaceModel.Type.PLACE,
            name = "Ивановские озёра",
            description = "Ивановские озёра - наиболее привлекательные места для туристов в данном районе, славятся живописностью заснеженных побережий. Являют собой каскад четырех самостоятельных водоемов с водопадами. Верхнее и нижнее озеро, расположенные в области вечных льдов, славятся они живописностью заснеженных побережий – обожаемых лыжниками. Отсюда начинается река Сарала, пробегающая между камнями около 200 метров навстречу нижнему озеру, ниспадая с высоты 40 метров. Глубина нижнего водоема, предположительно, достигает 147 метров. Природа, окружающая озера, действительно великолепна. Таёжные дебри, склоны гор. С одной стороны побережья скалы, а с другой глыбы камней (курумники). Окрестности богаты съедобными ягодами. В зимнее время Ивановские озера подходят для катания на лыжах и сноубордах, экскурсионных походов. Бронирование номеров: 8-913-509-84-90; 8-902-013-24-37. ",
            imgUrl = "https://cloud.pulse19.ru/uploads/2020/07/glavnaja-11-696x461.jpg"
        ),
        PlaceModel(
            id = 9,
            lat = 53.014024,
            lon = 90.314853,
            type = PlaceModel.Type.PLACE,
            name = "Улуг Хуртуях-Тас",
            description = "Каменное божество Улуг Хуртуях-Тас представляет собой трёхметровую стелу. Если верить археологическим данным, это сооружение было вкопано в землю, по разным данным от 6 до 4 тысяч лет назад на месте геологического разлома, где отмечено мощное излучение особой энергетики. Исследователи пришли к выводу, что излучение, идущее от стелы, не электрическое, не радиоактивное и не магнитное, но так и не смогли разгадать тайну этого загадочного явления. На сегодняшний день стела Хуртуах-Тас является местом паломничества и считается одним из самых почитаемых каменных изваяний.Ежегодно Хуртуях-Тас посещают тысячи туристов, а сторонники традиционных хакасских верований проводят у изваяния обряды: она считается защитницей материнства, помощницей беременных и бездетных. Контактный телефон: +7 (923) 582-20-49.",
            imgUrl = "http://asia-hotel.ru/wp-content/themes/website/data/php/timthumb.php?src=http://asia-hotel.ru/wp-content/uploads/2016/04/Hurtuyah_Tas_1.jpg&w=300&h=138"
        ),
        PlaceModel(
            id = 10,
            lat = 52.964889,
            lon = 89.784604,
            type = PlaceModel.Type.PLACE,
            name = "Аал Отты",
            description = "Малое поселение, в котором сохранились традиции. Население небольшое, национальный состав: хакасы. Контакный телефон администрации: +7 (390 45) 9-43-80.",
            imgUrl = "https://khakas.er.ru/media/news/December2021/TmfbP4cxVEZSD4Dfvos7.jpg"
        ),
        PlaceModel(
            id = 11,
            lat = 54.039246,
            lon = 91.487883,
            type = PlaceModel.Type.PLACE,
            name = "Гора Оглахты",
            description = "Каждый народ, приходивший на территорию Оглахты за последние 6000 лет, приносил сюда свою культуру, свои обычаи, оставляя после себя объекты материального наследия - петроглифы, стоянки, постройки и захоронения. Здесь расположены комплексы могильных курганов различных эпох и средневековая крепость, найдено и описано более 3,5 тысяч петроглифов. Уникальное сочетание истории, природы и красивейших ландшафтов делает горный массив Оглахты визитной карточкой Республики Хакасия. Контактный телефон: +7 (902) 011-74-54. ",
            imgUrl = "http://zapovednik-khakassky.ru/wp-content/uploads/2016/04/Gora-Sorok-zubev-uchastok-Oglahtyi-.jpg"
        ),
        PlaceModel(
            id = 12,
            lat = 54.704875,
            lon = 90.152301,
            type = PlaceModel.Type.PLACE,
            name = "Гора Чалпан",
            description = "Гора Чалпан находится на северо-западном берегу озера Белё и территориально относится к участку «Озеро Белё» Хакасского государственного природного биосферного заповедника. Историко-культурный комплекс участка «Озеро Белё» включает объекты на горе Чалпан: крепость тагаро-таштыкского времени, на южном склоне горы отмечено 3 писаницы, датируемые от 2 тысяч лет до н. э. до 8–9 веков., у подножия горы располагаются могильники тагарской и таштыкской культур. Чалпан представляет собой горное поднятие куэстообразной формы (в виде вытянутых гряд с несимметричными склонами: пологим и крутым) высотой 586,3 м над уровнем моря и площадью 200 га.",
            imgUrl = "https://bele.ru/media/cache/thumb_750_422/uploads/image/e390a77b374959fd45fb616447537e53e22cdd0d.jpg"
        ),
        PlaceModel(
            id = 13,
            lat = 54.444985,
            lon = 90.813230,
            type = PlaceModel.Type.PLACE,
            name = "Боярская писаница",
            description = "Боярская писаница включает две группы петроглифов: Большую и Малую. Обе группы были открыты в 1904 году ученым А.В. Адриановым. Высота стены небольшая, а вот длина значительная. Стена украшена древней выбивкой, которая рассказывает об истории Хакасии. Большая группа состоит из 130 изображений: бревенчатых изб, юрт, собак и пастухов, коз, оленей, коров и баранов, а также котлов, лучников и сцен из повседневной жизни древних народов. Малая группа включает 40 изображений такого же содержания. Датой создания Боярской писаницы является VII-III века до нашей эры. На сегодняшний день изображения петроглифов сгладились, заросли мхом, поэтому распознать их проблематично. Для того, чтобы улучшить качество и четкость изображений, советуем приезжать сюда на рассвете. В лучах восходящего солнца изображения становятся четче и словно оживают. Контактный телефон: +7 (3902) 248-026. Эл.почта: ookn@r-19.ru.  ",
            imgUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f7/SDIM1234.jpg/1200px-SDIM1234.jpg"
        ),
        PlaceModel(
            id = 14,
            lat = 52.82556,
            lon = 91.37111,
            type = PlaceModel.Type.PLACE,
            name = "Саяно-Шушенская ГЭС",
            description = "В нашей стране одной из самых мощных среди гидроэлектростанций является Саяно-Шушенская ГЭС, построенная на одной из великих рек Сибири Енисее. Уникальная арочно-гравитационная плотина станции высотой 242 м — самая высокая плотина России и одна из высочайших плотин мира. Название станции происходит от названий Саянских гор и расположенного неподалёку от станции села Шушенское. Контактный телефон: 8(800)333-80-00. Эл.почта: sges@rushydro.ru.",
            imgUrl = "http://mosenergoinform.ru/rushydronet/veer/4018-sajano-shushenskajaja-gjes.jpg"
        ),
        PlaceModel(
            id = 15,
            lat = 52.238609,
            lon = 89.377942,
            type = PlaceModel.Type.PLACE,
            name = "Горячий ключ",
            description = "Горячий ключ - это источник, который также именуют «Теплым ключом», «Абаканским источником». Он пользуется популярностью у населения Хакасии. Источник заключен в пределы бревенчатого домика, в полу которого имеется отверстие, ведущее вниз, к воде, где находится бревенчатый сруб с галечно-песочным дном. В этом-то срубе прямо из стены бьет горячий источник, температура воды в котором круглогодично равна +38°C. Сама вода не имеет ни вкуса, ни запаха, ни цвета и является пресной. Контактный номер: +7 (983) 194-93-37.",
            imgUrl = "https://cdn-zaryakubani.storage.yandexcloud.net/uploads/2021/04/GK_Olym-028.jpg"
        ),
        PlaceModel(
            id = 16,
            lat = 54.442404,
            lon = 89.804260,
            type = PlaceModel.Type.PLACE,
            name = "Туимский провал",
            description = "Туимский провал – это туристический объект техногенного происхождения, огромная впадина в горе с отвесными стенами на месте закрытого подземного рудника. После консервации рудника на вершине горы в результате обвала подземных горных выработок образовалась впадина, которая постоянно расширялась. На дне образовалось озеро с водой. В настоящее время диаметр провала достигает 200 м. Борта совершенно отвесные. Высота каменной стены составляет 125 метров. К озеру можно пройти по штольне или посмотреть сверху. Туимский провал открыл для широкой публики Юрий Сенкевич в 1995 году. Теперь это место паломничества экстрималов-дайверов и туристов. Контактный телефон: 8(923) 391-04-69.",
            imgUrl = "https://static.ngs.ru/turizm/images/0752bafb6ae0a2e53f68e8f43cd8c000.jpg"
        ),
        PlaceModel(
            id = 17,
            lat = 54.4501396,
            lon = 89.4543446,
            type = PlaceModel.Type.PLACE,
            name = "Тропа предков",
            description = "Всё это место от первого шага излучает высочайшие вибрации. Шаманы приходили на Тропу поклониться Богам и Духам, населявшим здешние многочисленные гроты, вознесенные на сотни метров над рекой Белый Июс. Тропа условно разделена на 7 секторов, которые символизируют рождение и становление личности человека... Тропа предков – пешеходный маршрут, пролегающий меж экзотических скал с гротами, где многие тысячелетия селились древние люди, где имеются наскальные рунические письмена и рисунки. Контактный телефон: 8(39035)9-12-26.",
            imgUrl = "https://sayanyeco.com/assets/galleries/132/predkov-5.jpg"
        ),
        PlaceModel(
            id = 18,
            lat = 53.7272427,
            lon = 91.4330506,
            type = PlaceModel.Type.HOTEL,
            imgUrl = "https://cf.bstatic.com/xdata/images/hotel/max1280x900/57475716.jpg?k=03a469c3eabca1d64b199aaa07a2862a09fe8c4234a550984606c8f6cfd697d5&o=&hp=1",
            name = "Бизнес-отель «Азия»",
            stars = 4,
            rating = 4.6f,
            price = 3500f
        ),
        PlaceModel(
            id = 19,
            lat = 53.7241839,
            lon = 91.4660944,
            type = PlaceModel.Type.HOTEL,
            imgUrl = "https://media-cdn.tripadvisor.com/media/photo-s/04/b2/85/02/caption.jpg",
            name = "Гостиница «Чалпан»",
            stars = 3,
            rating = 4.6f,
            price = 3460f
        ),
        PlaceModel(
            id = 20,
            lat = 53.7361332,
            lon = 91.4373942,
            type = PlaceModel.Type.HOTEL,
            imgUrl = "https://avatars.mds.yandex.net/get-altay/372953/2a0000015ebfa4667aa9ae3d7514c8534425/XL",
            name = "Гостиница «Дружба»",
            stars = 3,
            rating = 3.4f,
            price = 1700f
        ),
        PlaceModel(
            id = 21,
            lat = 53.7390861,
            lon = 91.4242402,
            type = PlaceModel.Type.HOTEL,
            imgUrl = "https://dosug-abakan.ru/upload/iblock/e08/e08209cf3db3ac8762643d17a321f2f9.jpg",
            name = "Комплекс апартаментов «Сибирь»",
            stars = 2,
            rating = 3.9f,
            price = 6058f
        ),
        PlaceModel(
            id = 22,
            lat = 53.7222208,
            lon = 91.4446173,
            type = PlaceModel.Type.HOTEL,
            imgUrl = "https://dosug-abakan.ru/upload/iblock/867/867f5b325812913e9097c10bccf6eb03.jpg",
            name = "Отель «Абакан»",
            stars = 4,
            rating = 4.2f,
            price = 3900f
        ),
        PlaceModel(
            id = 23,
            lat = 53.2888121,
            lon = 90.8675187,
            type = PlaceModel.Type.HOTEL,
            imgUrl = "https://cf.bstatic.com/xdata/images/hotel/max500/295035975.jpg?k=e457de558b7a5c696f82e80f42fc2ec9436a73ce4cf7073ed05179d0f69ca400&o=&hp=1",
            name = "Отель «Саяногорск»",
            stars = 3,
            rating = 4.4f,
            price = 2600f
        ),
        PlaceModel(
            id = 24,
            lat = 53.1032466,
            lon = 91.3850749,
            type = PlaceModel.Type.CAFE,
            imgUrl = "https://img.restaurantguru.com/w550/h367/r10d-Mama-Vsekh-Nakormit-advertisement.jpg",
            name = "Кафе «Мама всех накормит»",
            rating = 4.4f,
            price = 170f
        ),
        PlaceModel(
            id = 25,
            lat = 53.7243916,
            lon = 91.4173236,
            type = PlaceModel.Type.CAFE,
            imgUrl = "https://www.syemslona.ru/userfiles/models/news-images/news_480x1000/neftekamsk.jpg",
            name = "Столовая «Съем слона»",
            rating = 4.2f,
            price = 300f
        ),
        PlaceModel(
            id = 26,
            lat = 53.7282889,
            lon = 91.4271412,
            type = PlaceModel.Type.CAFE,
            imgUrl = "https://assets.allcafe.ru/k/places/canvas/930x530/pic/places/30537/ce1312a03235dcabfda68ce84d3fce66.jpeg",
            name = "Пиццерия «Тесоро»",
            rating = 3.8f,
            price = 160f
        ),
        PlaceModel(
            id = 27,
            lat = 53.7222222,
            lon = 91.4447557,
            type = PlaceModel.Type.CAFE,
            imgUrl = "https://media-cdn.tripadvisor.com/media/photo-s/0c/cd/2e/21/caption.jpg",
            name = "Ресторан «Mama Roma»",
            rating = 4.4f,
            price = 1000f
        ),
        PlaceModel(
            id = 28,
            lat = 53.1295457,
            lon = 90.5367231,
            type = PlaceModel.Type.CAFE,
            imgUrl = "https://10619-2.s.cdn12.com/rests/original/107_511125777.jpg",
            name = "Столовая «Сытый БАЙ»",
            rating = 4.5f,
            price = 220f
        ),
        PlaceModel(
            id = 29,
            lat = 53.7222862,
            lon = 91.4627924,
            type = PlaceModel.Type.CAFE,
            imgUrl = "https://img.restaurantguru.com/w550/h367/r2e9-interior-Burghuy-2021-09-1.jpg",
            name = "Кафе «Буржуй»",
            rating = 4.7f,
            price = 250f
        ),
        PlaceModel(
            id = 30,
            lat = 54.6549376,
            lon = 88.6876645,
            type = PlaceModel.Type.HOTEL,
            imgUrl = "https://www.24ajur.ru/img/main-gallery-8.jpg",
            name = "Гостиничный Комплекс «Ажур»",
            rating = 4.8f,
            price = 3780f
        ),
        PlaceModel(
            id = 31,
            lat = 53.0357051,
            lon = 90.3961429,
            type = PlaceModel.Type.HOTEL,
            imgUrl = "https://turbazy.ru/uploads/2017/09/snezhnyi-bars1.jpg",
            name = "Турбаза «Снежный барс»",
            rating = 4f,
            price = 5250f
        ),
        PlaceModel(
            id = 32,
            lat = 53.2888121,
            lon = 90.8675187,
            type = PlaceModel.Type.HOTEL,
            imgUrl = "https://avatars.mds.yandex.net/get-altay/787110/2a000001615744935db05af43e936af1c755/XXXL",
            name = "Гостиница «Енисей»",
            stars = 3,
            rating = 4.4f,
            price = 2300f
        ),
        PlaceModel(
            id = 33,
            lat = 53.1054925,
            lon = 91.3876138,
            type = PlaceModel.Type.CAFE,
            imgUrl = "https://dosug-abakan.ru/upload/iblock/3f3/3f376d7353b3bc75dbe48819ac92dc92.jpg",
            name = "Пиццерия «7 LiR»",
            rating = 4.2f,
            price = 260f
        ),
        PlaceModel(
            id = 34,
            lat = 53.1054925,
            lon = 91.3876138,
            type = PlaceModel.Type.CAFE,
            imgUrl = "https://media-cdn.tripadvisor.com/media/photo-s/0b/4a/05/b3/caption.jpg",
            name = "Кафе «Пирамида»",
            rating = 4f,
            price = 1200f
        ),
        PlaceModel(
            id = 35,
            lat = 53.7376454,
            lon = 91.4299494,
            type = PlaceModel.Type.CAFE,
            imgUrl = "https://avatars.mds.yandex.net/get-altay/474904/2a0000015c61a1af87231b9c4ef85995d0c8/XXL",
            name = "Пиццерия «Перцы»",
            rating = 4.4f,
            price = 1000f
        ),
        PlaceModel(
            id = 36,
            lat = 54.4609344,
            lon = 89.443200,
            type = PlaceModel.Type.HOTEL,
            imgUrl = "https://domik.travel/system/photos/pictures/000/099/926/mobile/photo.jpg?1579431953",
            name = "Отель «Зов предков»",
            rating = 4.8f,
            price = 400f
        ),
        PlaceModel(
            id = 37,
            lat = 53.7274375,
            lon = 91.4352827,
            type = PlaceModel.Type.CAFE,
            imgUrl = "https://kinf.ru/image/8/fm/bs8h3r03304m6wlj.jpg",
            name = "Кафе «Чайхана»",
            rating = 4.4f,
            price = 400f
        ),
        PlaceModel(
            id = 38,
            lat = 53.0837593,
            lon = 91.4148759,
            type = PlaceModel.Type.CAFE,
            imgUrl = "https://avatars.mds.yandex.net/get-altay/2044663/2a00000175ff2cc36027374eb5e49206856d/XXXL",
            name = "Ресторан «Чайхана»",
            rating = 4.4f,
            price = 300f
        ),
    )

}