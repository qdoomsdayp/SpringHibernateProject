/**
 * Created by wital on 29.01.2017.
 */
function selectList() {
    var id = document.getElementById("Tidrole").value;
    var dataJson = {idR: id};
    $.ajax({
        type: "POST",
        url: "/roles/select",
        data: JSON.stringify(dataJson),
        async: false,
        dataType: "json",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        //if received a response from the server
        success: function (data, textStatus, jqXHR) {
            //our country code was correct so we have some information to display
            loadRole(data,id);
            for (var i=0;i<data.length;i++){
                if (data[i].idR == id)
                    $("#Tnamer").val(data[i].nameR);
            }




        },
        error: function (data) {
            alert(data);
        }
    })
}
function del() {
    var dataJson = {idR: document.getElementById("Tidrole").value};
    $.ajax({
        type: "POST",
        url: "/roles/delete",
        data: JSON.stringify(dataJson),

        async: false,
        dataType: "json",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        //if received a response from the server
        success: function (data, textStatus, jqXHR) {


            $("#Tnamer").val("");
            loadRole(data,0);

        },
        error: function (data) {
            alert(data);
        }
    })
}
function edit() {
    if (document.getElementById("Tnamer").value != "") {
        var dataJson = {
            idR: document.getElementById("Tidrole").value,
            nameR: document.getElementById("Tnamer").value
        };

        $.ajax({
            type: "POST",
            url: "/roles/edit",
            data: JSON.stringify(dataJson),

            async: false,
            dataType: "json",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            //if received a response from the server
            success: function (data, textStatus, jqXHR) {
                //our country code was correct so we have some information to display

                $("#Tnamer").val("");
                loadRole(data,0);

            },
            error: function (data) {
                alert(data);
            }
        })
    }
}
function add() {
    if (document.getElementById("Tnamer").value != "") {
        var dataJson = {nameR: document.getElementById("Tnamer").value};
        $.ajax({
            type: "POST",
            url: "/roles/addR",
            data: JSON.stringify(dataJson),
            async: false,
            dataType: "json",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            //if received a response from the server
            success: function (data, textStatus, jqXHR) {
                $("#Tnamer").val("");
                loadRole(data,0);


            },
            error: function (data) {
                alert(data);
            }
        })
    }
}
function loadRole(listR,obj) {

    var tmpl = document.getElementById('templateRole').innerHTML.trim();
    tmpl = _.template(tmpl);
    document.getElementById('selectRole').innerHTML = tmpl({
        listR: listR,
        str: obj

    });
}