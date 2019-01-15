var ENV_MODE = "";
var USER;

$(function () {
    doService("/data/getUser", {}, function (rtnData) {
        USER = rtnData.msg;
        $("#currentUser").html(USER);
    })
});

function openScHtml() {
    ENV_MODE = "SC";
    $("#commitLogTable").datagrid("options").queryParams.mode = ENV_MODE;
    $("#commitLogTable").datagrid("reload");
    $("#commitInfoTable").datagrid("options").queryParams.mode = ENV_MODE;
    $("#commitInfoTable").datagrid("reload");
    $("#modeStr").html("（生产环境！请确认后再提交！）");
    $("#modeStr").css("color", "red");
    $("#selectModeDialog").dialog("close");
}

function openUatHtml() {
    ENV_MODE = "UAT";
    $("#commitLogTable").datagrid("options").queryParams.mode = ENV_MODE;
    $("#commitLogTable").datagrid("reload");
    $("#commitInfoTable").datagrid("options").queryParams.mode = ENV_MODE;
    $("#commitInfoTable").datagrid("reload");
    $("#modeStr").html("（测试环境）");
    $("#modeStr").css("color", "black");
    $("#selectModeDialog").dialog("close");
}

function openSelectModeDialog() {
    $("#selectModeDialog").dialog("open");
}

function logout() {
    $("#logoutForm").submit();
}

function commitPrepare(mode, uuid) {
    var param = {
        mode: mode,
        uuid: uuid
    };

    doService("/option/commitPrepare", param, function (rtnData) {
        $.messager.alert({
            width: 380,
            msg: rtnData.msg,
            title: 'success',
        });

        reloadTable();
    })
}

function cancelPrepare(mode, uuid, logUuid) {
    var param = {
        mode: mode,
        uuid: uuid,
        logUuid: logUuid
    };

    doService("/option/cancelPrepare", param, function (rtnData) {
        $.messager.alert({
            width: 380,
            msg: rtnData.msg,
            title: 'success',
        });

        reloadTable();
    })
}

function reloadTable() {
    $("#commitLogTable").datagrid("reload");
    $("#commitInfoTable").datagrid("reload");
}

function formatterCommitLogOption(value, row, index) {
    var btn = "<a href='#' onclick='commitPrepare(\"" + ENV_MODE + "\",\"" + row.UUID + "\")'>提交发布</a>&nbsp;&nbsp;";
    return btn;
}

function formatterCommitInfoOption(value, row, index) {
    var btn = "<a href='#' onclick='cancelPrepare(\"" + ENV_MODE + "\",\"" + row.UUID + "\",\"" + row.COMMITUUID + "\")'>取消发布</a>&nbsp;&nbsp;";
    return btn;
}