/**
 * Created by feng on 2017/6/2 0002.
 */
$(function(){
    //Add the change skin listener
    $("[data-skin]").on('click', function (e) {
        if($(this).hasClass('knob'))
            return;
        e.preventDefault();
        change_skin($(this).data('skin'));
    });
});
var my_skins = [
    "skin-blue",
    "skin-black",
    "skin-red",
    "skin-yellow",
    "skin-purple",
    "skin-green",
    "skin-blue-light",
    "skin-black-light",
    "skin-red-light",
    "skin-yellow-light",
    "skin-purple-light",
    "skin-green-light"
];

/**
 * Replaces the old skin with the new skin
 * @param String cls the new skin class
 * @returns Boolean false to prevent link's default action
 */
function change_skin(cls) {
    $.each(my_skins, function (i) {
        $("body").removeClass(my_skins[i]);
    });

    $("body").addClass(cls);
    return false;
}