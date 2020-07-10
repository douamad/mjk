import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MjkSharedModule } from 'app/shared/shared.module';
import { ObjetSaisineComponent } from './objet-saisine.component';
import { ObjetSaisineDetailComponent } from './objet-saisine-detail.component';
import { ObjetSaisineUpdateComponent } from './objet-saisine-update.component';
import { ObjetSaisineDeleteDialogComponent } from './objet-saisine-delete-dialog.component';
import { objetSaisineRoute } from './objet-saisine.route';

@NgModule({
  imports: [MjkSharedModule, RouterModule.forChild(objetSaisineRoute)],
  declarations: [ObjetSaisineComponent, ObjetSaisineDetailComponent, ObjetSaisineUpdateComponent, ObjetSaisineDeleteDialogComponent],
  entryComponents: [ObjetSaisineDeleteDialogComponent],
})
export class MjkObjetSaisineModule {}
