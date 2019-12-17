function tostring(s, len) {
    var str = s + '.000000001';

    if (str.indexOf('.') == -1)return str;
    var ss = str.split('.');
    var p1 = ss[0];
    var p2 = (ss[1] + '0000000000').substr(0, len);

    if (p2 == '0')p2 = '';
    ss = p1;
    if (p2.length > 0)ss += '.' + p2;
    return ss
}
function ajax_form_pars(form) {
    var pars = {};
    $(form).find('input').each(function () {
        if ($(this).attr('type') == 'radio' || $(this).attr('type') == 'checkbox') {
            if ($(this).prop('checked')) {
                pars[$(this).attr('name')] = $(this).val()
            }
        } else {
            pars[$(this).attr('name')] = $(this).val()
        }
    });
    $(form).find('select').each(function () {
        pars[$(this).attr('name')] = $(this).val()
    });
    $(form).find('textarea').each(function () {
        pars[$(this).attr('name')] = $(this).val()
    });
    pars['is_api'] = '1';
    pars['is_ajax'] = '1';
    pars['ajax'] = '1';
    //name arrary
    if ($(form).find('input[name="name"]').length > 1) {
        var namearr = new Array();
        $(form).find('input[name="name"]').each(function (i) {
            namearr[i] = $(this).val();
        });
        pars['name'] = namearr;
    }

    return pars
}
function init_ajax_link_form() {
    if (location.href.indexOf('127.0.0.1') != -1 && 0) {
        $('.ajaxform').attr('target', 'b');
        $('.ajaxform').submit(function () {
            setTimeout('location.reload()', 100)
        });
        return
    }

    $('.ajaxlink').click(function () {
        $('.formmsg').remove();
        if ($(this).parent().find('.formmsg').length == 0) {
            $(this).parent().append('<span class="formmsg label label-success">loading ...</span>')
        }
        $.get($(this).attr('href'), function (data) {
        });
        return false
    })

    $('.ajaxform').submit(function () {
        if ($('.ajaxform_go').length > 0) {
            return true;
        }

        if ($(this).parent().find('input[type="submit"]').length > 0) {
            $(this).parent().find('input[type="submit"]').eq(0).before('<span class="formmsg alert alert-warning"></span>')
        } else {
            if ($(this).parent().find('.formmsg').length == 0) {
                $(this).parent().append('<span class="formmsg aler1t alert-warning"></span>')
            }
        }

        $(this).parent().find('.formmsg').show();
        $(this).parent().find('.formmsg').html('loading ...');

        var ac = $(this).attr('action');
        if (!ac)ac = location.href;
        var pars = ajax_form_pars(this);

        if ($(this).attr('enctype') == 'multipart/form-data') {
            pars = new FormData(this);

            $.ajax({
                url: ac,
                type: 'POST',
                data: pars,
                processData: false,
                contentType: false,
                success: function (data) {
                    if (data == '1') {
                        $('.formmsg').html('<span class="glyphicon glyphicon-ok"></span>')
                    } else {
                        $('.formmsg').html(data)
                    }
                    ajax_form_after(data)
                }
            });
        } else {
            $.post(ac, pars, function (data) {
                if (data == '1') {
                    $('.formmsg').html('<span class="glyphicon glyphicon-ok"></span>')
                } else {
                    $('.formmsg').html(data)
                }
                ajax_form_after(data)
            });
        }
        return false
    })
}
function ajax_form_after(data) {
    setTimeout("$('.formmsg').remove()", 5000);
    $('.chk').prop('checked', false);
    $('.btn_selectall').attr('rel', '0')
}
function dict2str(dict) {
    var keys = new Array();
    var isarray = false;
    for (a in dict) {
        keys.push(a);
        if (a == 0)isarray = true
    }
    keys.sort();
    var s1 = '{';
    var s2 = '}';
    if (isarray) {
        s1 = '[';
        s2 = ']'
    }
    var s = s1;
    for (var a = 0; a < keys.length; a++) {
        var subs = dict[keys[a]];
        var tp = typeof(dict[keys[a]])
        if (tp == "object") {
            subs = dict2str(subs)
        }
        if (isarray) {
            s += subs + ','
        } else {
            s += "'" + keys[a] + "':" + (tp != "object" ? "'" + subs + "'" : subs) + ","
        }
    }
    s += s2;
    s = s.replace(',' + s2, s2);
    return s
}
var mdloading_count = 0;
var mdloading_hanlder = null;

var mdloading_string = '';
function mdloading(s, a) {

    if (a == 1) {
        mdloading_string += s;
    } else {
        mdloading_string = mdloading_string.replace(s, '');
        mdloading_string = mdloading_string.replace(s, '');
    }

    if($('.mdloading_string').length>0){
        $('.mdloading_string').html(mdloading_string);
    }

    if($('.mdloading').length==0){
        $('body').append('<div class="modal mdloading">    <div class="modal-dialog modal-sm">        <div class="modal-content">            <div class="modal-body">        ' +
            '    <p>  Loading ...     </p>   ' +
            '<p><img src="/static/images/load3.gif" alt=""  style="max-width: 200px;"  /></p>   ' +
            '  </div>        </div>    </div></div>')
    }


    if (mdloading_string.length > 0) {
        $('.mdloading').modal({'show': true, 'backdrop': true, 'keyboard': true});
    } else {
        $('.mdloading').modal('hide')
    }


}