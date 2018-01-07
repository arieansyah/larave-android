<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Lapangan extends Model
{
  protected $table = 'lapangans';
  protected $primaryKey = 'id_lapangan';

  protected $fillable = ['nama', 'alamat', 'harga', 'handphone'];
}
