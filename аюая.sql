USE test; 
DROP TABLE IF EXISTS notes_task; 
CREATE TABLE `notes_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL DEFAULT 'Без Имени',
  `content` mediumtext NOT NULL,
  `parent` int(11) NOT NULL DEFAULT '0',
  `priority` int(11) NOT NULL DEFAULT '1',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_done` tinyint(1) NOT NULL DEFAULT '0',
  `is_task` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`) VALUES ('Что сказал графолог  ', 
'после изучения твоего почерка?– Сказал, что я злой и агрессивный. – Ну, а ты? – Накостылял ему за вранье… ',
 '2');
INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Брелок-', 
'это такая штучка, которая позволяет потерять все ключи сразу.',
 '2','0','0');
INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Захватить мир', 
'после дождика в четверг',
 '10','0','1');
  INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Сходить в магаз', 
'Хлеб,пиво,майонез',
 '5','1','1');
INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Ты чего не бреешься?', 
'– Нету у меня девушки, для которой хотелось бы побриться. – А для себя? – А для себя я пиво покупаю.',
 '2','0','0');
  INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('У кирпичей свое полетное расписание', '',
 '2','0','0');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Пиво или сок', 
'Пивной сок',
 '2','0','0');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Разобраться', 
'что курили разработчики Спринг',
 '6','0','1');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Фантазия кончается', 
'Я пишу это руками , а не копирую из программы типа Workbanch',
 '10','1','1');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Какая-то задача', 
'ага, покушать бы',
 '7','0','1');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Успею?', 
'или нет сдать задание',
 '10','0','0');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Здоровья шо ли', 
'наверно тяжко наши программы проверять',
 '5','0','0');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Скучноо', 
'спеть шо ли',
 '5','0','1');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Посмотреть кинцо', 
'давно попкорна не ел',
 '3','1','1');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Доделать пейджинг', 
'на самом деле вроде он и сделан, но по хорошему точки нужны',
 '10','0','1');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('точка, точка, запятая', 
'вышла рожица кривая',
 '5','0','0');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Война и мир', 
'в животе.',
 '3','0','0');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('тоооооскаааа', 
'в общем пошел я как-то заниматься программированием, а тааааам...',
 '2','0','0');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Надо постричься', 
'а то как-то не комильфо...',
 '5','0','1');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('у нас -20', 
'Хорошо тепло, снег идет, красссооота',
 '3','1','1');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('ЗА поле parent', 
'хотел сделать возможность разбития задач на подзадачи, но не успел, дедлайн настал ,сушим весла. Когда-нить доделаю',
 '5','0','1');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Тыц,тыц', 
'Мееертвый ананрхист, панки хой!',
 '7','0','0');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Узнать', 
'Бывают ли тимлидеры и сеньоры любителями рэпа?',
 '10','0','1');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Что более важно', 
'Есть ли жизнь на джаве в детскопе?',
 '10','0','0');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Тыкнуть кошку', 
'она цапнула в ответ',
 '3','1','1');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Уже больше 20', 
'Я крут...',
 '3','0','0');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Вот кому надо', 
'ваще , и вааааааще, и та ,и сяк, и эдае. Воооот...',
 '4','0','0');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Интересно', 
'это кто-нибудь прочтет? Если да, привет, мужик! Скоро все закончится, терпения тебе!',
 '8','1','1');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Ээээх', 
'жисссть моя жестянка, вокруг одни пиявки, лягушки,фу, какая гадость!',
 '4','0','0');
 INSERT INTO `test`.`notes_task` (`title`, `content`, `priority`,`is_done`,`is_task`) VALUES ('Задолбало', 
'Все, надоело, план минимум выполнен',
 '10','0','1');