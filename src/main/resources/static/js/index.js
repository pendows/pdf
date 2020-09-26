/* 加载页面时，第一个文本框获取焦点 */
window.onload = function ()
{
    document.getElementById("spzb1").focus();
    var pzzl = "2";
    var spzb = "19（0831）44证明";
    var tfrq = "2019-08-31";
    var spbh = "60010316";

    var year = parseInt(tfrq.substring(0, 4));
    if (year >= 2019)
    {
        var spzh = "";
        if (pzzl == 7)
        {
            spzh = spbh;
        } else
        {
            spzh = spzb + spbh;
        }
        $('#spzh').val(spzh);
        $('#spzbhm').hide();
        $('#spzb1').hide();
        $('#spzb2').hide();
        $('#spbh').hide();
        $('#spzh').show();

    } else
    {
        if (pzzl == 7)
        {
            var spzb1 = "";
            var spzb2 = spzb;
            $('#spzbhm').hide();
            $('#namezpzh').text("查验码：");
        } else
        {
            var spzb1 = spzb.substring(1, 4);
            var spzb2 = spzb.substring(5, spzb.length);
            $('#spbh').val(spbh);
        }
        document.formCy.spzb1.value = spzb1;
        document.formCy.spzb2.value = spzb2;
    }
};

/* 提交查验前校验参数 */
var isSubmiting = false;

/**
 * 查验
 * @returns {boolean}
 */
function btn_tj()
{
    if (isSubmiting)
    {
        alert("请刷新界面后再提交您的申请！");
        return false;
    }
    //判断填发日期为空
    if (document.formCy.tfrq.value == "")
    {
        alert("请输入填发日期!");
        document.formCy.tfrq.focus();
        return false;
    }
    //判断金额是否为空
    if (document.formCy.hjje.value.replace(/(^\s*)|(\s*$)/g, "") == "")
    {
        alert("请输入金额合计！");
        document.formCy.hjje.focus();
        return false;
    }

    if ($('#spzh').val() == '' || $('#spzh').val() == undefined)
    {
        //判断电子税票号码是否为空、
        if (document.formCy.spbh.value == "")
        {//税票编号为空
            alert("请填写完整的税收票证字号!");
            document.formCy.spbh.focus();
            return false;
        }
        if (document.formCy.spzb1.value == "" && document.formCy.spbh.value == "")
        {//税票字轨和税票编号都为空
            alert("请填写完整的税收票证字号!");
            document.formCy.spzb1.focus();
            return false;
        }
    }
    if (document.getElementById("veryCode"))
    {//有验证码表单元素则要验证码
        //判断验证码是否为空
        if (document.formCy.veryCode.value.replace(/(^\s*)|(\s*$)/g, "") == "")
        {
            alert("请输入验证码！");
            document.formCy.veryCode.focus();
            return false;
        } else
        {

        }
    }
    /* 提交查验 */
    isSubmiting = true;
    var spzh;
    var spbh;
    var spzb1;
    var spzb2;
    if (isSubmiting)
    {
        //验证码不为空
        if (document.getElementById("veryCode"))
        { //有验证码表单元素则要验证码

            var tfrq = document.formCy.tfrq.value;
            if (parseInt(tfrq.substr(0, 4)) >= 2019)
            {
                //19年以后采用新规则字号
                spzh = $('#spzh').val();
                spzh = spzh.replace("No", "");
                if (spzh.length == 15)
                {
                    //若15位则判断为清单
                    spzb = "个税纳税清单";
                    spbh = spzh;
                } else
                {
                    spbh = spzh.substr(spzh.length - 8);
                    spzb = spzh.substr(0, spzh.length - 8);
                }
            } else
            {
                spzb1 = $('#spzb1').val();
                spzb2 = $('#spzb2').val();
                if (spzb2 == "个税纳税清单")
                {
                    spzb = spzb2;
                } else
                {
                    spzb = "(" + spzb1 + ")" + spzb2;
                }
                spbh = $('#spbh').val();
            }

            //合计金额
            var hjje = document.formCy.hjje.value.replace(/(^\s*)|(\s*$)/g, "");
            var veryCode = document.formCy.veryCode.value
            fsAsynDataLoader.blockPage('正在加载数据，请您稍等...');
            $.ajax({
                type: "POST",
                url: "/etax/dzsp/dzspdy/dzspCy.do",
                data: {spzb: spzb, spbh: spbh, tfrq: tfrq, hjje: hjje, veryCode: veryCode},
                dataType: "json",
                success: function (data)
                {
                    if (data.err == "error")
                    {
                        fsAsynDataLoader.unBlockPage();
                        window.location.href = "/etax/dzsp/dzspdy/yzmError.do";
                        return false;
                    } else
                    {

                        fsAsynDataLoader.unBlockPage();
                        window.location.href = "/etax/dzsp/dzspdy/dzspCy2.do?tid=" + data.tid;
                    }
                }
            });
        }
        else//验证码为空
        {
            //税票字别
            var tfrq = document.formCy.tfrq.value;
            if (parseInt(tfrq.substr(0, 4)) >= 2019)
            {
                //19年以后采用新规则字号
                spzh = $('#spzh').val();
                spzh = spzh.replace("No", "");
                if (spzh.length == 15)
                {
                    //若15位则判断为清单
                    spzb = "个税纳税清单";
                    spbh = spzh;
                } else
                {
                    spbh = spzh.substr(spzh.length - 8);
                    spzb = spzh.substr(0, spzh.length - 8);
                }
            } else
            {
                spzb1 = $('#spzb1').val();
                spzb2 = $('#spzb2').val();
                if (spzb2 == "个税纳税清单")
                {
                    spzb = spzb2;
                } else
                {
                    spzb = "(" + spzb1 + ")" + spzb2;
                }
                spbh = $('#spbh').val();
            }

            //合计金额
            var psel = document.getElementById("hjje").value;
            var $fileId = document.getElementById("fileId").value;
            fsAsynDataLoader.blockPage('正在加载数据，请您稍等...');
            console.log('test');
            //var pdf = "http://2fmiiz.natappfree.cc/generateQR?id="+$fileId+"&fileAmout="+hjje+"&fileDate="+tfrq+"&title=国家税务总局广东省电子税务局";
            window.location.href = "/static/js/pdfview.html?id="+$fileId+"&fileAmout="+psel+"&fileDate="+tfrq
            //window.open('/static/js/index.html', "PDF",'height=1000, width=1000, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
            fsAsynDataLoader.unBlockPage();
        }
    }
}

function isMobile()
{
    var userAgentInfo = navigator.userAgent.toLowerCase();
    var Agents = new Array("android", "iphone", "symbianos", "windows phone", "ipad", "ipod", "ucweb");
    var flag = false;
    for (var v = 0; v < Agents.length; v++)
    {
        if (userAgentInfo.indexOf(Agents[v]) > 0)
        {
            flag = true;
            break;
        }
    }
    return flag;
}

function spzbChange()
{
    var isgsqd = $('#spzb2').val();
    if (isgsqd == "个税纳税清单")
    {
        $('#namespzh').text("查验码：");
        $('#spzbhm').hide();
    } else
    {
        $('#spzbhm').show();
        $('#namespzh').text("税票字号：");
    }
}

function tfrqChange()
{
    var tfrq = $('#tfrq').val();
    var year = tfrq.substr(0, 4);
    if (parseInt(year) >= 2019)
    {
        $('#spzbhm').hide();
        $('#spzb1').hide();
        $('#spzb2').hide();
        $('#spbh').hide();
        $('#spzh').show();

    } else
    {
        $('#spzbhm').show();
        $('#spzb1').show();
        $('#spzb2').show();
        $('#spbh').show();
        $('#spzh').hide();
    }

}
