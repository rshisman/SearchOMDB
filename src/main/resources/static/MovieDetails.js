Vue.component('movie-details', {
    template: `
    <div v-if="movie !== undefined" class="movie-details row">   
        <div id="poster" class="col-sm-6">    
             <img :src="movie.poster">           
        </div>        
        <div id="info" class="form col-sm-6">   
            <h3 class="from-control">{{movie.title}}</h3>
            <h3 class="from-control">{{movie.releaseDate}}</h3>                   
        </div>  
                    
    </div>`,
    props: {
        movieId: Number
    },
    data() {
        return {
            movie: undefined
        }
    },
    created() {
        this.movie = this.getMovieById(this.movieId);
    },
    methods: {
        getMovieById(id) {
            let search = 'http://localhost:8080/api/movies/' + id;
            axios.get(search)
                .then((response) => {
                    this.movie = response.data;
                })
                .catch((error) => {
                    console.log(error);
                });
        },
    }
});