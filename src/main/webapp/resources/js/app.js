function renderMenu(){
    // Script para marcar como activa la opción del menu que fue seleccionada
    var url = '#{id_active_item}'+'.xhtml';
    // Remove active for all items.
    $('.page-sidebar-menu li').removeClass('active');
    // highlight submenu item
    $('li a[href="' + url + '"]').parent().addClass('active');

    // Highlight parent menu item.
    $('ul a[href="' + url + '"]').parents('li').addClass('active');
}

function buildBreadcrum( strForSplit ){
    if( strForSplit == null || strForSplit.length < 1 ){
        return;
    }
    var breadcrum =  document.getElementsByTagName('ol')[0];

    if( breadcrum != null ){
        while( breadcrum.hasChildNodes() ){
            breadcrum.removeChild(breadcrum.childNodes[0]);
        }
        breadcrum.appendChild(getHomeOption());

        items = strForSplit.split(',');
        if( items.length > 0 ){
            option = items[0];
            active = items[1];
            link = items[2];

            optionItem = document.createElement("li");
            //optionItem.setAttribute("class","active");
            optionItemContentText = document.createTextNode(option);
            optionItem.appendChild(optionItemContentText);
            breadcrum.appendChild(optionItem);

            activeItem = document.createElement("li");
            //activeItem.setAttribute("class","active");
            activeItem.appendChild(document.createTextNode(active));
            breadcrum.appendChild(activeItem);

        }
    }

    function getHomeOption(){
        //<li><a href="#"><i class="fa fa-home"></i></a></li>
        li = document.createElement("li");
        a = document.createElement("a");
        a.setAttribute("href", "index.xhtml");
        i = document.createElement("i");
        i.setAttribute("class", "fa fa-home");
        a.appendChild(i);
        li.appendChild(a);
        return li;
    }

    function resetSelectOneMenu(idMenu,textLabel){
        document.getElementById(idMenu).innerHTML = textLabel;
    }

}