Vue.component('movie-details', {
    template: `

    <div class="movie-details row">
        <div  v-if="movie === undefined">Fetching data...</div>
        <div v-if="movie !== undefined" id="poster" class="col-sm-5">    
             <img :src="movie.poster">           
        </div>    
         
         <div v-if="movie !== undefined" id="info" class="form col-sm-7">
                 
            <span class="detailsTitle">{{movie.title}}</span><br>
            <span class="detailsDate">{{movie.releaseDate}}</span>
            <br><br><br><br><br>
            
            <table class="detailsTable">
              <tbody>
                <tr>
                  <td class="labelTD">Genre:</td>
                  <td class="valueTD">{{movie.genre}}</td>
                </tr>
                <tr>
                  <td class="labelTD">Director:</td>
                  <td class="valueTD">{{movie.director}}</td>
                </tr>
                <tr>
                  <td class="labelTD">Actors:</td>
                  <td class="valueTD">{{movie.actors}}</td>
                </tr>
                <tr>
                  <td class="labelTD">Plot:</td>
                  <td class="valueTD">{{movie.plot}}</td>
                </tr>
                <tr>
                  <td class="labelTD">IMDB Rating:</td>
                  <td class="valueTD">{{movie.imdbRating}}</td>
                </tr>
                <tr v-if="movie.website !== 'N/A'">
                  <td class="labelTD">Website:</td>
                  <td class="valueTD"><a :href="movie.website" target="_blank">{{movie.website}}</a></td>
                </tr>
              </tbody>
            </table>                 
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