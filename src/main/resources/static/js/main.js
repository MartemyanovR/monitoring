/**
 *
 * @type {Vue} объект для приветствия
 */
let app = new Vue({
    el:'#app',
    data: {
        greeting: 'Hello user!'
    }
});

/**
 * Создания объекта "resource" из библиотеки
 * vue-resource, для отправки HTTP запросов
 * и получение ответов  от сервера по URL "/kkt{/id}"
 */
let kktApi = Vue.resource('/kkt{/id}');

/**
 *Компонент для отображения
 * содержимого массива из "kkt-list"
 */
Vue.component('kkt-row', {
    props: ['k'],
    template: '<li><i>({{k.zn}})</i> {{k.model}}</li>'

});

/**
 * Главный компонент. Получает пустой массив и
 * в хуке created заполняет его данными с сервера
 * через get запрос, используя vue-resource API.
 * И далее передает в template массив "kkt",
 * где в цикле for выводит по элементно в
 * компонент kkt-row
 *
 */
Vue.component('kkt-list', {
    props: ['kkt'],
    template:
        '<ul>' +
        '<kkt-row v-for="k in kkt" v-bind:k="k" v-bind:key="k.zn"/>' +
        '</ul>' ,
    created: function () {
        kktApi.get().then(result=>
        result.json().then(data=>
            data.forEach(k => this.kkt.push(k))
            )
        )
    }
});

/**
 *Привязываем объект Vue к div с идентификатором app1.
 * Создаем массив "kkts" для получение данных в
 * формате json с сервера.
 * @type {Vue}
 */
let app1 = new Vue({
    el: '#app1',
    template: '<kkt-list v-bind:kkt="kkts"></kkt-list>',
    data: {
        kkts: []
    }
});