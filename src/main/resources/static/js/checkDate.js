$(function () {
    $('#datetimepicker1').datetimepicker();
    $('#datetimepicker1').change(function () {
        $("#datetimepicker").attr('value', $('#datetimepicker1').val());
    });
});
$(function () {
    $('#datetimepicker_1').datetimepicker();
    $('#datetimepicker_1').change(function () {
        $("#datetimepicker_0").attr('value', $('#datetimepicker_1').val());
    });
});