Vue.component('movie-overview', {
    template: `
    <div class="movie-item">
        <img :src="movie.poster" @click="onMovieClicked()"/>
    </div>`,
    props: {
        movie: {
            type: Object
        }
    },
    data: function () {
        return {
        }
    },
    methods: {
        onMovieClicked() {

        }
    }
});