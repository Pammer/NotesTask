( function( $ ) {
    var isEdit = true;
    $(document).ready(function() {
        $("#title").attr('required', '');
        $("span.trash").click(function(event){
            event.preventDefault();
            event.stopPropagation();
            var $trash = $(this);
            $.get(
                "delete",
                { id: $(this).closest(".sticker").data("id")},
                function(data) {
                    if("ok" == data){
                        $trash.closest(".sticker").remove();
                        vex.dialog.alert("Заметка успешно удалена");
                    } else{
                        vex.dialog.alert("Ошибка. Заметка не удалена");
                    }
                }
            );
        });
        $(".close").click(function (event) {
            $("#title").val("");
            $("#content").val("");
            $("#priority").val("1");
            $("#undone").prop('checked', true);
            $("#task").prop('checked', true);
            isEdit = true;
        });
        $("#add").click(function(event){
            isEdit = false;
        });
        var noteInfo;
        $(".badge").click(function(event){
            if(!$(this).parent().is("#add")){
                var title =  $("#title");
                var content =  $("#content");
                var priority =  $("#priority");
                var done =  $("#done");
                var task =  $("#task");
                $.get(
                    "notes",
                    { id: $(this).closest(".sticker").data("id")},
                    function(data) {
                        if ($.trim(data)){
                            noteInfo = data;
                            title.val(noteInfo.title);
                            content.val(noteInfo.content);
                            priority.val(noteInfo.priority);
                            if(noteInfo.done == 0){
                                $("#undone").prop('checked', true);
                            } else {
                                $("#done").prop('checked', true);
                            }
                            if(noteInfo.task){
                                $("#task").prop('checked', true);
                            }else{
                                $("#note").prop('checked', true);
                            }
                        }
                    }
                );
            }
            $('#openModal').css({'display':'block'});
            window.location = '#openModal';
        });
        $(".button").click(function(event){
            event.preventDefault();
            if ($("#title").val() === "" || $("#title").val().length > 44){
                vex.dialog.alert("Не ввели заголовок, либо он больше 44 символов");
                return;
            }

            var stickers = $(".stickers");
            var data = $('form').serialize();
            if(isEdit){
                //<![CDATA[
                data = data + '&' + $.param({ 'id': noteInfo.id });
                //]]>

            }
            $.post({
                url : isEdit ? 'update' : 'addNote',
                data : data,
                success : function(res) {
                    $('#openModal').css({'display':'none'});
                    if("ok" == res){
                        vex.dialog.alert("Заметка успешно " + (isEdit ? "отредактирована" : "создана") + ", обновите страницу");
                        $(".alert").text = "Заметка успешно создана";
                    } else{
                        vex.dialog.alert("Ошибка " +  (isEdit ? "редактирования" : "создания") );
                        $(".alert").text = "Ошибка создания.";
                    }

                }
            })
        });
        $('nav li ul').hide().removeClass('fallback');
        $('nav li').hover(
            function () {
                $('ul', this).stop().slideDown(100);
            },
            function () {
                $('ul', this).stop().slideUp(100);
            }
        );
    });
} )( jQuery );
